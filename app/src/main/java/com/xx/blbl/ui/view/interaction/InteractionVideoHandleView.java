package com.xx.blbl.ui.view.interaction;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.xx.blbl.R;
import com.xx.blbl.model.interaction.InteractionEdgeDimensionModel;
import com.xx.blbl.model.interaction.InteractionEdgeModel;
import com.xx.blbl.model.interaction.InteractionEdgeQuestionChoiceModel;
import com.xx.blbl.model.interaction.InteractionEdgeQuestionModel;
import com.xx.blbl.model.interaction.InteractionModel;
import com.xx.blbl.model.interaction.InteractionStoryModel;
import com.xx.blbl.model.interaction.InteractionVariableModel;

import java.util.ArrayList;
import java.util.List;

/** Interactive-video choice rendering without implicit player, DI or Fragment casts. */
public final class InteractionVideoHandleView extends FrameLayout implements View.OnClickListener {
    public interface Callback {
        void onPauseVideo();

        /** For a nonzero CID, the host updates video CID/edge ID and loads the selected story. */
        void onChoice(InteractionEdgeQuestionChoiceModel choice);
    }

    private final AppCompatTextView variableText;
    private final ArrayList<InteractionVariableModel> variables = new ArrayList<>();
    private InteractionModel model;
    private Callback callback;
    private long currentCid, displayedCid;
    private int viewportWidth, viewportHeight;

    public InteractionVideoHandleView(Context context) {
        this(context, null);
    }

    public InteractionVideoHandleView(Context context, AttributeSet attributes) {
        super(context, attributes);
        variableText = new AppCompatTextView(context);
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);
        variableText.setLayoutParams(params);
        variableText.setPadding(20, 20, 20, 20);
        variableText.setBackgroundResource(R.drawable.transparent_black_bg);
        variableText.setTypeface(null, 1);
    }

    public void setVideoViewport(int width, int height) {
        viewportWidth = width;
        viewportHeight = height;
    }

    private void setupView(InteractionEdgeModel edge) {
        List<InteractionEdgeQuestionModel> questions = edge.getQuestions();
        if (questions == null) return;
        removeAllViews();
        for (InteractionEdgeQuestionModel question : questions) {
            if (question.getPause_video() == 1 && callback != null) callback.onPauseVideo();
            List<InteractionEdgeQuestionChoiceModel> choices = question.getChoices();
            if (choices == null) continue;
            if (question.getType() == 2) {
                for (InteractionEdgeQuestionChoiceModel choice : choices) {
                    if (!TextUtils.isEmpty(choice.getCondition()) && !a(choice.getCondition()))
                        continue;
                    AppCompatButton button = makeButton(choice);
                    int[] position = {choice.getX(), choice.getY()};
                    InteractionEdgeModel activeEdge = model == null ? null : model.getEdges();
                    InteractionEdgeDimensionModel dimension =
                            activeEdge == null ? null : activeEdge.getDimension();
                    if (dimension != null)
                        position =
                                InteractionLogic.choicePosition(
                                        choice.getX(),
                                        choice.getY(),
                                        dimension.getWidth(),
                                        dimension.getHeight(),
                                        viewportWidth > 0 ? viewportWidth : getWidth(),
                                        viewportHeight > 0 ? viewportHeight : getHeight());
                    FrameLayout.LayoutParams params =
                            new FrameLayout.LayoutParams(
                                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params.setMargins(position[0], position[1], 0, 0);
                    button.setLayoutParams(params);
                    button.setClickable(true);
                    button.setFocusable(true);
                    addView(button);
                }
                if (getChildCount() > 0)
                    post(
                            () -> {
                                View first = getChildAt(0);
                                if (first != null) first.requestFocus();
                            });
            } else {
                LinearLayout row = new LinearLayout(getContext());
                row.setOrientation(LinearLayout.HORIZONTAL);
                row.setPadding(20, 10, 20, 30);
                FrameLayout.LayoutParams params =
                        new FrameLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.BOTTOM;
                addView(row, params);
                for (InteractionEdgeQuestionChoiceModel choice : choices) {
                    if (!TextUtils.isEmpty(choice.getCondition()) && !a(choice.getCondition()))
                        continue;
                    AppCompatButton button = makeButton(choice);
                    LinearLayout.LayoutParams buttonParams =
                            new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
                    buttonParams.gravity = Gravity.CENTER;
                    buttonParams.setMarginStart(5);
                    buttonParams.setMarginEnd(5);
                    row.addView(button, buttonParams);
                }
                if (row.getChildCount() > 0) row.post(() -> row.getChildAt(0).requestFocus());
            }
        }
        e();
    }

    private AppCompatButton makeButton(InteractionEdgeQuestionChoiceModel choice) {
        AppCompatButton button = new AppCompatButton(getContext());
        button.setText(choice.getOption());
        button.setBackgroundResource(R.drawable.button_common);
        button.setTag(choice);
        button.setOnClickListener(this);
        return button;
    }

    public boolean a(String expression) {
        return InteractionLogic.condition(variables, expression);
    }

    public InteractionVariableModel b(String id) {
        return InteractionLogic.find(variables, id);
    }

    public void c(String assignment) {
        InteractionLogic.assign(variables, assignment);
    }

    public void d() {
        List<InteractionStoryModel> stories = model == null ? null : model.getStory_list();
        if (stories == null) return;
        for (InteractionStoryModel story : stories) {
            InteractionEdgeModel edge = model.getEdges();
            if (edge != null
                    && edge.getQuestions() != null
                    && !edge.getQuestions().isEmpty()
                    && displayedCid != story.getCid()) {
                setupView(edge);
                displayedCid = story.getCid();
            }
        }
    }

    public void e() {
        if (variables.isEmpty()) return;
        int shown = 0;
        for (InteractionVariableModel variable : variables) if (variable.is_show() == 1) shown++;
        StringBuilder text = new StringBuilder();
        for (int index = 0; index < variables.size(); index++) {
            InteractionVariableModel variable = variables.get(index);
            if (variable.is_show() == 1) {
                text.append(variable.getName()).append('：').append(variable.getValue());
                if (index != shown - 1) text.append('\n');
            }
        }
        if (variableText.getParent() == null && text.length() > 0) addView(variableText);
        variableText.setText(text);
    }

    public void f(long progress) {
        List<InteractionStoryModel> stories = model == null ? null : model.getStory_list();
        if (stories == null) return;
        for (InteractionStoryModel story : stories) {
            InteractionEdgeModel edge = model.getEdges();
            if (story.getCid() == currentCid
                    && progress >= story.getStart_pos()
                    && edge != null
                    && edge.getQuestions() != null
                    && !edge.getQuestions().isEmpty()
                    && displayedCid != story.getCid()) {
                setupView(edge);
                displayedCid = story.getCid();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == null || !(view.getTag() instanceof InteractionEdgeQuestionChoiceModel)) return;
        InteractionEdgeQuestionChoiceModel choice =
                (InteractionEdgeQuestionChoiceModel) view.getTag();
        if (callback != null && choice.getCid() != 0) callback.onChoice(choice);
        removeAllViews();
        String action = choice.getNative_action();
        if (action != null) for (String assignment : action.split(";", -1)) c(assignment);
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public long getCurrentCid() {
        return currentCid;
    }

    public void setCurrentCid(long cid) {
        currentCid = cid;
    }

    public void setModel(InteractionModel model) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(model, "m");
        this.model = model;
        List<InteractionVariableModel> incoming = model.getHidden_vars();
        if (incoming != null) {
            for (InteractionVariableModel variable : incoming)
                if (b(variable.getId_v2()) == null) variables.add(variable);
            e();
        }
    }
}
