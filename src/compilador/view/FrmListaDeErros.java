package compilador.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.JTextArea;

public class FrmListaDeErros extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblListaDeErros;
	private JTextArea textArea;
	private JPanel panel;
	private GridBagLayout gbl_panel;
	private GridBagConstraints gbc_lblListaDeErros;
	private GridBagConstraints gbc_textArea;

	public FrmListaDeErros(List<String> listaDeErros) {
		this.initComponents();
		this.setResizable(false);
		this.popularAreaDeTexto(listaDeErros);
	}

	private void initComponents() {
		contentPane = new JPanel();
		panel = new JPanel();
		gbl_panel = new GridBagLayout();
		gbc_lblListaDeErros = new GridBagConstraints();
		gbc_lblListaDeErros.gridwidth = 2;
		gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		lblListaDeErros = new JLabel("Detalhes do erro");
		textArea = new JTextArea();
		
		this.setTitle("Erro no código");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.CENTER);

		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };

		panel.setLayout(gbl_panel);

		gbc_lblListaDeErros.insets = new Insets(0, 0, 5, 0);
		gbc_lblListaDeErros.gridx = 0;
		gbc_lblListaDeErros.gridy = 0;

		panel.add(lblListaDeErros, gbc_lblListaDeErros);

		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;

		panel.add(textArea, gbc_textArea);
		
		textArea.setEditable(false);
		
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void popularAreaDeTexto(List<String> lista) {
		lista.forEach(erros -> {
			this.textArea.append(erros.toString() + "\n");
		});
	}
	
}