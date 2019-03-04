import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SQLFormatterToolWindow implements ToolWindowFactory {

    private JPanel toolWindowContent;
    private JButton buttonFormat;
    private JTextArea textArea;
    private JLabel labelMessage;
    private JCheckBox removeNewLineCode;

    private Formatter sqlFormatter = new BasicFormatterImpl();

    public SQLFormatterToolWindow() {
        buttonFormat.addActionListener(e -> {
            labelMessage.setText("");
            String text = textArea.getText();
            boolean shouldRemoveNewLineCode = removeNewLineCode.isSelected();
            if (shouldRemoveNewLineCode) {
                text = text.replace("\r", "").replace("\n", "");
            }
            String formattedText = sqlFormatter.format(text);
            textArea.setText(formattedText);

            SQLFormatterConfig currentConfig = SQLFormatterConfig.getInstance();
            if (currentConfig != null) {
                currentConfig.setRemoveNewLineCode(shouldRemoveNewLineCode);
                SQLFormatterConfig.getInstance().loadState(currentConfig);
            }
        });
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        SQLFormatterConfig config = SQLFormatterConfig.getInstance();
        if (config.isRemoveNewLineCode()) {
            removeNewLineCode.setSelected(true);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        toolWindowContent = new JPanel();
        toolWindowContent.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        buttonFormat = new JButton();
        buttonFormat.setText("Format");
        toolWindowContent.add(buttonFormat, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        toolWindowContent.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textArea = new JTextArea();
        scrollPane1.setViewportView(textArea);
        labelMessage = new JLabel();
        labelMessage.setForeground(new Color(-2215827));
        labelMessage.setText("");
        toolWindowContent.add(labelMessage, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeNewLineCode = new JCheckBox();
        removeNewLineCode.setSelected(false);
        removeNewLineCode.setText("Remove new line code");
        toolWindowContent.add(removeNewLineCode, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return toolWindowContent;
    }

}
