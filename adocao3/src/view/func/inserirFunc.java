package view.func;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import banco.funcionarioBanco;
import model.Funcionarios;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

public class inserirFunc {

    protected Shell shell;
    private Text textNomeFuncionario;
    private Text textDepFuncionario;
    private funcionarioBanco funcionarioBanco;
    private Label lblNomeDoFuncionario;
    private Label lblDepartamento;
    private Label lblCadastrarFuncionario;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            inserirFunc window = new inserirFunc();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirFunc() {
        funcionarioBanco = new funcionarioBanco();  // Inicialize o objeto aqui
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents(display); // Passar o display para o método
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        if (backgroundImage != null && !backgroundImage.isDisposed()) {
            backgroundImage.dispose();
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents(Display display) {
        shell = new Shell(display);
        createResourceManager();
        shell.setSize(600, 400);
        shell.setText("Inserir Funcionário");

        // Carrega a imagem de fundo
        backgroundImage = new Image(display, "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsFuc.jpg");
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Adiciona o PaintListener para desenhar a imagem de fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                if (backgroundImage != null) {
                    e.gc.drawImage(backgroundImage, 0, 0,
                                   backgroundImage.getBounds().width, backgroundImage.getBounds().height,
                                   0, 0, shell.getBounds().width, shell.getBounds().height);
                }
            }
        });

        textNomeFuncionario = new Text(shell, SWT.BORDER);
        textNomeFuncionario.setBounds(180, 144, 241, 30);

        textDepFuncionario = new Text(shell, SWT.BORDER);
        textDepFuncionario.setBounds(180, 180, 241, 30);

        Button btnOK = new Button(shell, SWT.NONE);
        btnOK.setBounds(441, 295, 97, 34);
        btnOK.setText("OK");

        // Definir as cores do botão "OK"
        Color backgroundOK = new Color(display, 0, 255, 0); // Verde
        Color foregroundOK = new Color(display, 255, 255, 255); // Branco
        btnOK.setBackground(backgroundOK);
        btnOK.setForeground(foregroundOK);

        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                Funcionarios funcionario = new Funcionarios();
                funcionario.setNomeFuncionario(textNomeFuncionario.getText());
                funcionario.setDepFuncionario(textDepFuncionario.getText());

                funcionarioBanco.inserirFunc(funcionario);

                MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                messagebox.setMessage("Funcionário inserido com sucesso");
                messagebox.open();
            }
        });

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setBounds(46, 295, 97, 34);
        btnCancelar.setText("Cancelar");

        // Definir as cores do botão "Cancelar"
        Color backgroundCancel = new Color(display, 255, 0, 0); // Vermelho
        Color foregroundCancel = new Color(display, 255, 255, 255); // Branco
        btnCancelar.setBackground(backgroundCancel);
        btnCancelar.setForeground(foregroundCancel);

        lblNomeDoFuncionario = new Label(shell, SWT.NONE);
        lblNomeDoFuncionario.setBounds(55, 144, 119, 15);
        lblNomeDoFuncionario.setText("Nome do Funcionário:");

        lblDepartamento = new Label(shell, SWT.NONE);
        lblDepartamento.setBounds(95, 180, 79, 15);
        lblDepartamento.setText("Departamento:");

        Label lblTitulo = new Label(shell, SWT.CENTER);
        lblTitulo.setAlignment(SWT.LEFT);
        lblTitulo.setText("Cadastrar Funcionário");
        lblTitulo.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblTitulo.setBounds(243, 10, 119, 15);
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textNomeFuncionario.setText("");
                textDepFuncionario.setText("");

                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
