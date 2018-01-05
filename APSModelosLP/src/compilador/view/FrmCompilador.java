package compilador.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.SwingConstants;
import compilador.parser.Compilador;
import compilador.parser.ParseException;
import compilador.util.MyFileFilter;
import static javax.swing.JOptionPane.*;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FrmCompilador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private JPanel panel;
	private GridBagLayout gbl_panel;
	private GridBagConstraints gbc_lblAnalisadorDaLinguagem;
	private GridBagConstraints gbc_btnAbrirArquivo;
	private GridBagConstraints gbc_btnSair;
	private JLabel lblAnalisadorDaLinguagem;
	private JButton btnAbrirArquivo;
	private JButton btnSair;
	private Compilador compilador;
	private List<String> listaErros;
	private FrmListaDeErros listaDeErros;

	public FrmCompilador() {
		this.setBounds(new Rectangle(0, 0, 200, 140));
		this.setTitle("ANALISADOR L\u00C9XICO E SINT\u00C1TICO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
		this.initListeners();
		this.getContentPane().setLayout(null);
		this.getContentPane().add(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private void initComponents() {

		panel = new JPanel();
		gbl_panel = new GridBagLayout();
		gbc_lblAnalisadorDaLinguagem = new GridBagConstraints();
		gbc_btnAbrirArquivo = new GridBagConstraints();
		gbc_btnSair = new GridBagConstraints();
		lblAnalisadorDaLinguagem = new JLabel("Analisador da Linguagem LP");
		btnAbrirArquivo = new JButton("Abrir arquivo");
		btnSair = new JButton("Sair");
		listaErros = new ArrayList<String>();

		panel.setBounds(0, 0, 194, 112);

		gbl_panel.columnWidths = new int[] { 220, 0 };
		gbl_panel.rowHeights = new int[] { 36, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };

		panel.setLayout(gbl_panel);

		gbc_lblAnalisadorDaLinguagem.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnalisadorDaLinguagem.gridx = 0;
		gbc_lblAnalisadorDaLinguagem.gridy = 0;

		lblAnalisadorDaLinguagem.setHorizontalAlignment(SwingConstants.CENTER);

		gbc_btnAbrirArquivo.fill = GridBagConstraints.VERTICAL;
		gbc_btnAbrirArquivo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbrirArquivo.gridx = 0;
		gbc_btnAbrirArquivo.gridy = 1;

		gbc_btnSair.gridx = 0;
		gbc_btnSair.gridy = 2;

		lblAnalisadorDaLinguagem.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAbrirArquivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel.add(lblAnalisadorDaLinguagem, gbc_lblAnalisadorDaLinguagem);
		panel.add(btnAbrirArquivo, gbc_btnAbrirArquivo);
		panel.add(btnSair, gbc_btnSair);
	}

	private void initListeners() {
		btnAbrirArquivo.addActionListener(al -> {
			this.analisaCaminho();
		});

		btnSair.addActionListener(al -> {
			System.exit(0);
		});
	}

	private void analisaCaminho() {
		Path path = this.getFilePath();
		if (path != null && Files.exists(path)) {
			showMessageDialog(null, "Arquivo aberto com sucesso!");
			if (processaResultado(path)) {
				showMessageDialog(null, "Análise feita com sucesso!", "Linguagem reconhecida", INFORMATION_MESSAGE);
			} else {
				this.setVisible(false);
				this.exibeFalhas();
			}
		}
	}

	private Path getFilePath() {
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Escolha o arquivo");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new MyFileFilter());
		//fileChooser.setAcceptAllFileFilterUsed(true);
		int result = fileChooser.showOpenDialog(this);		
		if (result == JFileChooser.CANCEL_OPTION)
			return null;
		else if(!(fileChooser.getSelectedFile().getName().endsWith(".lp"))){
			showMessageDialog(null, "Formato de Arquivo Inválido");
			return null;
		}
		
		return fileChooser.getSelectedFile().toPath();
	}

	private boolean processaResultado(Path path) {

		String arquivo = path.toString();
		try {
			compilador = new Compilador(new FileInputStream(arquivo));
			compilador.INIT();
		} catch (FileNotFoundException | ParseException | Error exception) {
			System.out.println(exception.getMessage());
			listaErros.add(exception.getMessage());
			return false;
		}

		return true;
	}

	private void exibeFalhas() {
		this.listaDeErros = new FrmListaDeErros(listaErros);
		this.listaDeErros.setVisible(true);
		this.listaDeErros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}