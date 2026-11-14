package com.xx.blbl.ui.view;

import com.xx.blbl.model.interaction.InteractionVariableModel;
import com.xx.blbl.ui.view.interaction.InteractionLogic;
import com.xx.blbl.ui.view.keyboard.KeyboardInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/** Pure host-side tests for keyboard key data and interactive-video business rules. */
public final class RecoveredWidgetLogicTest {
    private static int assertions;

    private static void same(Object expected, Object actual) {
        assertions++;
        if (!Objects.equals(expected, actual))
            throw new AssertionError("expected=" + expected + "; actual=" + actual);
    }

    private static InteractionVariableModel variable(String id, float value) {
        InteractionVariableModel variable = new InteractionVariableModel();
        variable.setId_v2(id);
        variable.setValue(value);
        return variable;
    }

    private static void keyboard() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < alphabet.length(); i++)
            same(String.valueOf(alphabet.charAt(i)), KeyboardInput.alphabetKey(i));
        Set<String> keys = new HashSet<>();
        for (int i = 0; i < 9; i++)
            for (String key : KeyboardInput.t9Keys(i)) if (key != null) keys.add(key);
        same(36, keys.size());
        for (int i = 0; i < alphabet.length(); i++)
            same(true, keys.contains(String.valueOf(alphabet.charAt(i))));
        same("A", KeyboardInput.trimKey(" A "));
        same("", KeyboardInput.trimKey(" \t\n\r"));
        same("", KeyboardInput.trimKey(""));
        same("你好", KeyboardInput.trimKey("\u0000你好\u001f"));
        same("\u00a0A\u00a0", KeyboardInput.trimKey("\u00a0A\u00a0"));
        same("A B", KeyboardInput.trimKey(" A B "));
        same("1", KeyboardInput.t9Keys(0)[1]);
        String[] mutable = KeyboardInput.t9Keys(0);
        mutable[0] = "bad";
        same("0", KeyboardInput.t9Keys(0)[0]);
    }

    private static void interaction() {
        List<InteractionVariableModel> vars = new ArrayList<>();
        vars.add(variable("score", 10));
        vars.add(variable("lives", 3));
        same(true, InteractionLogic.condition(vars, "score>=10"));
        same(false, InteractionLogic.condition(vars, "score>10"));
        same(true, InteractionLogic.condition(vars, "score<=10"));
        same(false, InteractionLogic.condition(vars, "score<10"));
        same(true, InteractionLogic.condition(vars, "score==10"));
        same(false, InteractionLogic.condition(vars, "score==9"));
        same(true, InteractionLogic.condition(vars, "score>-1.5"));
        same(true, InteractionLogic.condition(vars, "score>=+10.0"));
        same(true, InteractionLogic.condition(vars, "unknown>999"));
        same(true, InteractionLogic.condition(vars, "score>invalid"));
        same(true, InteractionLogic.condition(vars, ""));
        same(true, InteractionLogic.condition(vars, "score==10==20"));
        same(true, InteractionLogic.assign(vars, "score=score+2.5"));
        same(12.5f, InteractionLogic.find(vars, "score").getValue());
        same(true, InteractionLogic.assign(vars, "score=score-1.5"));
        same(11f, InteractionLogic.find(vars, "score").getValue());
        InteractionLogic.assign(vars, "score=lives*4");
        same(12f, InteractionLogic.find(vars, "score").getValue());
        InteractionLogic.assign(vars, "score=score/3");
        same(4f, InteractionLogic.find(vars, "score").getValue());
        InteractionLogic.assign(vars, "score=-2.5");
        same(-2.5f, InteractionLogic.find(vars, "score").getValue());
        InteractionLogic.assign(vars, "score=unknown+7");
        same(0f, InteractionLogic.find(vars, "score").getValue());
        InteractionLogic.assign(vars, "score=not-a-number");
        same(0f, InteractionLogic.find(vars, "score").getValue());
        InteractionLogic.assign(vars, "score=+2");
        same(2f, InteractionLogic.find(vars, "score").getValue());
        same(false, InteractionLogic.assign(vars, "missing equals"));
        same(2f, InteractionLogic.find(vars, "score").getValue());
        same(false, InteractionLogic.assign(vars, ""));
        same(false, InteractionLogic.assign(vars, null));
        InteractionLogic.assign(vars, "score=lives/0");
        same(Float.POSITIVE_INFINITY, InteractionLogic.find(vars, "score").getValue());
        same(null, InteractionLogic.find(vars, "absent"));
        same(2, vars.size());
        same(
                "[640, 720]",
                Arrays.toString(InteractionLogic.choicePosition(640, 360, 1920, 1080, 1920, 1080)));
        same(
                "[960, 720]",
                Arrays.toString(InteractionLogic.choicePosition(640, 360, 1920, 1080, 2560, 1080)));
        same(
                "[640, 900]",
                Arrays.toString(InteractionLogic.choicePosition(640, 360, 1920, 1080, 1920, 1440)));
        same("[1, 1]", Arrays.toString(InteractionLogic.choicePosition(1, 1, 3, 3, 2, 2)));
    }

    public static void main(String[] args) {
        keyboard();
        interaction();
        System.out.println(
                "WIDGET_LOGIC_OK assertions="
                        + assertions
                        + " apk_inputs=0 android_runtime_required=false");
    }
}
