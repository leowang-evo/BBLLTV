package com.xx.blbl.ui.view.interaction;

import com.xx.blbl.model.interaction.InteractionVariableModel;

import java.util.List;
import java.util.regex.Pattern;

/** Recovered conditional and assignment rules for interactive-video choices. */
public final class InteractionLogic {
    private static final Pattern NUMBER = Pattern.compile("^([-+])?\\d+(\\.\\d+)?$");

    private InteractionLogic() {}

    private static boolean numeric(String value) {
        return !value.isEmpty() && NUMBER.matcher(value).matches();
    }

    public static InteractionVariableModel find(
            List<InteractionVariableModel> variables, String id) {
        for (InteractionVariableModel variable : variables)
            if (java.util.Objects.equals(variable.getId_v2(), id)) return variable;
        return null;
    }

    public static boolean condition(List<InteractionVariableModel> variables, String expression) {
        for (String operator : new String[] {">=", "<=", "==", "<", ">"}) {
            int position = expression.indexOf(operator);
            if (position >= 0) {
                String id = expression.substring(0, position),
                        right = expression.substring(position + operator.length());
                // Kotlin split uses every delimiter occurrence; only the first two tokens were
                // read.
                int repeated = right.indexOf(operator);
                if (repeated >= 0) right = right.substring(0, repeated);
                InteractionVariableModel variable = find(variables, id);
                if (variable == null || !numeric(right)) return true;
                float left = variable.getValue(), value = Float.parseFloat(right);
                if (operator.equals(">=")) return left >= value;
                if (operator.equals("<=")) return left <= value;
                if (operator.equals("==")) return left == value;
                if (operator.equals("<")) return left < value;
                return left > value;
            }
        }
        return true;
    }

    public static boolean assign(List<InteractionVariableModel> variables, String expression) {
        if (expression == null || expression.isEmpty()) return false;
        try {
            int assignment = expression.indexOf('=');
            String id = expression.substring(0, assignment),
                    right = expression.substring(assignment + 1);
            float value = 0f;
            boolean arithmetic = false;
            for (String operator : new String[] {"+", "-", "*", "/"}) {
                int position = right.indexOf(operator);
                if (position > 0) {
                    arithmetic = true;
                    String source = right.substring(0, position),
                            number = right.substring(position + 1);
                    int repeated = number.indexOf(operator);
                    if (repeated >= 0) number = number.substring(0, repeated);
                    InteractionVariableModel variable = find(variables, source);
                    if (variable != null && numeric(number)) {
                        float left = variable.getValue(), operand = Float.parseFloat(number);
                        if (operator.equals("+")) value = left + operand;
                        else if (operator.equals("-")) value = left - operand;
                        else if (operator.equals("*")) value = left * operand;
                        else value = left / operand;
                    }
                    break;
                }
            }
            if (!arithmetic && numeric(right)) value = Float.parseFloat(right);
            for (InteractionVariableModel variable : variables)
                if (java.util.Objects.equals(variable.getId_v2(), id)) variable.setValue(value);
            return true;
        } catch (RuntimeException malformed) {
            return false;
        }
    }

    private static int roundToInt(double value) {
        if (Double.isNaN(value)) throw new IllegalArgumentException("Cannot round NaN value.");
        if (value > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (value < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) Math.round(value);
    }

    public static int[] choicePosition(
            int x, int y, int width, int height, int viewportWidth, int viewportHeight) {
        double aspect = ((double) width) / height;
        if (((double) viewportWidth) / viewportHeight >= aspect) {
            double scale = ((double) viewportHeight) / height;
            return new int[] {
                roundToInt((viewportWidth - width * scale) / 2d + x * scale),
                (int) ((height - y) * scale)
            };
        }
        double scale = ((double) viewportWidth) / width;
        return new int[] {
            roundToInt(x * scale),
            (int) ((viewportHeight - height * scale) / 2d + (height - y) * scale)
        };
    }
}
