package view.doenca;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import java.time.LocalDate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import banco.doencaBanco;
import model.Doencas;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;

public class InserirDoenca {

    protected Shell shell;
    private Text txtNomeDoenca;
    private Text txtTratamentoDoenca;
    private Text txtCodProntuario;
    private doencaBanco doencaBanco;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            InserirDoenca window = new InserirDoenca();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InserirDoenca() {
        doencaBanco = new doencaBanco(); // Instancia o banco de dados
    }

    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(600, 500);
        shell.setText("Inserir Doença no Prontuário");
        
        

        // Adiciona imagem de fundo
        String backgroundImagePath = "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsDoen.jpg";
        Image backgroundImage = new Image(Display.getDefault(), backgroundImagePath);
        shell.setBackgroundImage(backgroundImage);

        // Labels e campos de entrada de dados
        Label lblNomeDoenca = new Label(shell, SWT.NONE);
        lblNomeDoenca.setText("Insira o nome da doença:");
        lblNomeDoenca.setBounds(114, 215, 168, 20);

        txtNomeDoenca = new Text(shell, SWT.BORDER);
        txtNomeDoenca.setBounds(288, 212, 168, 30);

        Label lblDataDoenca = new Label(shell, SWT.NONE);
        lblDataDoenca.setText("Insira a data da doença:");
        lblDataDoenca.setBounds(114, 254, 168, 20);

        DateTime dateDataDoenca = new DateTime(shell, SWT.BORDER);
        dateDataDoenca.setBounds(288, 251, 168, 38);

        Label lblTratamento = new Label(shell, SWT.NONE);
        lblTratamento.setText("Insira o tratamento da doença:");
        lblTratamento.setBounds(114, 300, 168, 20);

        txtTratamentoDoenca = new Text(shell, SWT.BORDER);
        txtTratamentoDoenca.setBounds(288, 297, 168, 30);

        Label lblCodProntuario = new Label(shell, SWT.NONE);
        lblCodProntuario.setText("Insira o código do prontuário:");
        lblCodProntuario.setBounds(114, 335, 168, 20);

        txtCodProntuario = new Text(shell, SWT.BORDER);
        txtCodProntuario.setBounds(288, 332, 168, 30);

        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(367, 405, 168, 30);
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnInserir.setText("Inserir Doença");

        Label lblInserirDoencas = new Label(shell, SWT.NONE);
        lblInserirDoencas.setBounds(193, 171, 84, 15);
        lblInserirDoencas.setText("Anotar Doenças");
        
        Label lblInsirirDoenas = new Label(shell, SWT.NONE);
        lblInsirirDoenas.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblInsirirDoenas.setBounds(253, 44, 132, 30);
        lblInsirirDoenas.setText("Insirir Doenças");

        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    LocalDate data = LocalDate.of(dateDataDoenca.getYear(),
                            dateDataDoenca.getMonth() + 1, // Ajuste necessário para o DateTime
                            dateDataDoenca.getDay());

                    String nomeDoenca = txtNomeDoenca.getText();
                    String tratamentoDoenca = txtTratamentoDoenca.getText();
                    Integer codProntuario = Integer.parseInt(txtCodProntuario.getText());

                    Doencas doenca = new Doencas(nomeDoenca, data, tratamentoDoenca, codProntuario);

                    // Chama o método para inserir a doença
                    doencaBanco.inserirDoenca(doenca);

                    // Mensagem de sucesso
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Doença inserida com sucesso!");
                    messagebox.open();

                } catch (Exception ex) {
                    // Mensagem de erro em caso de falha
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao inserir a doença: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });
    }
    private void createResourceManager() {
    	localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
    }
}
