package org.hvdw.jexiftoolgui.view;

import ch.qos.logback.classic.Logger;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.hvdw.jexiftoolgui.MyVariables;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;

public class FoundMetaData extends JDialog {
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JTable foundmetadatatable;
    private JButton OKbutton;
    private JButton Cancelbutton;
    private JLabel foundMetadataLabel;

    private JPanel jp = null;
    private String metadata = "";

    private final static Logger logger = (Logger) LoggerFactory.getLogger(FoundMetaData.class);


    public FoundMetaData() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(OKbutton);

        OKbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logger.debug("User closes the \"display found metadata\" panel");
                setVisible(false);
                dispose();
            }
        });
        Cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // mouse table listener
        foundmetadatatable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) foundmetadatatable.getModel();
                int selectedRowIndex = foundmetadatatable.getSelectedRow();
                MyVariables.setselectedLensConfig(model.getValueAt(selectedRowIndex, 1).toString());
                String img_name = model.getValueAt(selectedRowIndex, 1).toString();
            }
        });
    }


    private void displayfoundmetadata(List<String> foundMetadata) {
        DefaultTableModel model = (DefaultTableModel) foundmetadatatable.getModel();
        model.setColumnIdentifiers(new String[]{ResourceBundle.getBundle("translations/program_strings").getString("smd.imgname"), ResourceBundle.getBundle("translations/program_strings").getString("smd.keyorvalue"), ResourceBundle.getBundle("translations/program_strings").getString("smd.foundstring")});
        foundmetadatatable.getColumnModel().getColumn(0).setPreferredWidth(250);
        foundmetadatatable.getColumnModel().getColumn(1).setPreferredWidth(70);
        foundmetadatatable.getColumnModel().getColumn(2).setPreferredWidth(500);
        model.setRowCount(0);
        String secondcolumn = "";
        String thirdcolumn = "";

        foundMetadataLabel.setText(ResourceBundle.getBundle("translations/program_strings").getString("smd.foundmetadata") + " \"" + MyVariables.getSearchPhrase() + "\".");
        Object[] row = new Object[1];
        for (String metadata : foundMetadata) {
            logger.debug(metadata);
            String[] cells = metadata.split("\\t");
            if ("value-key".equals(cells[1])) {
                secondcolumn = ResourceBundle.getBundle("translations/program_strings").getString("smd.value");
                //secondcolumn = ResourceBundle.getBundle("translations/program_strings").getString("smd.value") + " (" + ResourceBundle.getBundle("translations/program_strings").getString("smd.key") + ")";
                //thirdcolumn = ResourceBundle.getBundle("translations/program_strings").getString("smd.value") + ": " + cells[2] + " (" + ResourceBundle.getBundle("translations/program_strings").getString("smd.key") + ": " + cells[3] + ")";
                thirdcolumn = cells[2] + " (" + ResourceBundle.getBundle("translations/program_strings").getString("smd.key") + ": " + cells[3] + ")";
            } else {
                secondcolumn = ResourceBundle.getBundle("translations/program_strings").getString("smd.key");
                //secondcolumn = ResourceBundle.getBundle("translations/program_strings").getString("smd.key") + " (" + ResourceBundle.getBundle("translations/program_strings").getString("smd.value") + ")";
                //thirdcolumn = ResourceBundle.getBundle("translations/program_strings").getString("smd.key") + ": " + cells[2] + " (" + ResourceBundle.getBundle("translations/program_strings").getString("smd.value") + ": " + cells[3] + ")";
                thirdcolumn = cells[2] + " (" + ResourceBundle.getBundle("translations/program_strings").getString("smd.value") + ": " + cells[3] + ")";
            }
            model.addRow(new Object[]{cells[0], secondcolumn, thirdcolumn});
        }


    }


    private void onCancel() {
        // add your code here if necessary
        setVisible(false);
        dispose();
    }

    public String showDialog(JPanel rootpanel, List<String> foundMetadata) {
        pack();
        //setLocationRelativeTo(null);
        setLocationByPlatform(true);
        setTitle(ResourceBundle.getBundle("translations/program_strings").getString("smd.foundmetadatatitle"));
        // Make table readonly
        foundmetadatatable.setDefaultEditor(Object.class, null);

        displayfoundmetadata(foundMetadata);

        setVisible(true);
        return metadata;
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
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(15, 15, 15, 15), -1, -1));
        contentPane.setPreferredSize(new Dimension(1024, 600));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), 5, 5));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        foundMetadataLabel = new JLabel();
        this.$$$loadLabelText$$$(foundMetadataLabel, this.$$$getMessageFromBundle$$$("translations/program_strings", "smd.foundmetadata"));
        panel2.add(foundMetadataLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel2.add(scrollPane, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        foundmetadatatable = new JTable();
        scrollPane.setViewportView(foundmetadatatable);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        panel1.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        OKbutton = new JButton();
        this.$$$loadButtonText$$$(OKbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "dlg.ok"));
        panel3.add(OKbutton);
        Cancelbutton = new JButton();
        this.$$$loadButtonText$$$(Cancelbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "dlg.cancel"));
        Cancelbutton.setVisible(false);
        panel3.add(Cancelbutton);
    }

    private static Method $$$cachedGetBundleMethod$$$ = null;

    private String $$$getMessageFromBundle$$$(String path, String key) {
        ResourceBundle bundle;
        try {
            Class<?> thisClass = this.getClass();
            if ($$$cachedGetBundleMethod$$$ == null) {
                Class<?> dynamicBundleClass = thisClass.getClassLoader().loadClass("com.intellij.DynamicBundle");
                $$$cachedGetBundleMethod$$$ = dynamicBundleClass.getMethod("getBundle", String.class, Class.class);
            }
            bundle = (ResourceBundle) $$$cachedGetBundleMethod$$$.invoke(null, path, thisClass);
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(path);
        }
        return bundle.getString(key);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
