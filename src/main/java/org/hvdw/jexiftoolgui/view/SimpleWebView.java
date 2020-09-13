package org.hvdw.jexiftoolgui.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.hvdw.jexiftoolgui.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleWebView extends JDialog {

    private JButton OKbutton;
    private JEditorPane webEditorPane;
    private JScrollPane webScrollPane;
    private JPanel contentPane;

    private final HyperlinkListener linkListener = new LinkListener();
    private final static ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(SimpleWebView.class);

    public SimpleWebView() {

        setContentPane(contentPane);
        setModal(true);
        this.setIconImage(Utils.getFrameIcon());


        OKbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }

    public void HTMLView(String title, String message, int vWidth, int vHeight) {
        String htmlStart = "<html><style>p, body, tr, th, td {font-family: helvetica; } </style><body><center><table border=0 width=" + String.valueOf(vWidth) + "px><tr><td>";
        String htmlEnd = "</td></tr></center></body></html>";
        String html = htmlStart + message + htmlEnd;
        webEditorPane.setContentType("text/html");
        webEditorPane.setEditable(false);
        contentPane.setPreferredSize(new Dimension(((int) Math.round(vWidth + (float) (vWidth / 3))), ((int) Math.round(vHeight + (float) (vHeight / 8)))));

        setTitle(title);
        webEditorPane.setText(html);
        webEditorPane.addHyperlinkListener(linkListener);
        pack();
        double x = getParent().getBounds().getCenterX();
        double y = getParent().getBounds().getCenterY();
        //setLocation((int) x - getWidth() / 2, (int) y - getHeight() / 2);
        setLocationRelativeTo(null);
        setVisible(true);
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
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setPreferredSize(new Dimension(-1, -1));
        webScrollPane = new JScrollPane();
        contentPane.add(webScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        webEditorPane = new JEditorPane();
        webEditorPane.setBackground(new Color(-855310));
        webEditorPane.setContentType("text/html");
        webEditorPane.setEditable(false);
        webEditorPane.setMinimumSize(new Dimension(-1, -1));
        webEditorPane.setPreferredSize(new Dimension(-1, -1));
        webEditorPane.setText("<html>\n  <head>\n    \n  </head>\n  <body>\n  </body>\n</html>\n");
        webScrollPane.setViewportView(webEditorPane);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        OKbutton = new JButton();
        OKbutton.setText("OK");
        panel1.add(OKbutton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}