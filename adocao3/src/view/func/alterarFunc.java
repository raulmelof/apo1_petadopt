package view.func;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import banco.funcionarioBanco;
import model.Funcionarios;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;


public class alterarFunc {

    protected Shell shell;
    private Text textidFuncionario;
    private Text textnomeFuncionario;
    private Text textdepFuncionario;
    private funcionarioBanco funcionarioBanco;
    private Label lblNomeDoFuncionario;
    private Label lblDepartamento;
    private Label lblInsiraOsNovos;
    private Label lblDigiteOId;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            alterarFunc window = new alterarFunc();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public alterarFunc() {
        funcionarioBanco = new funcionarioBanco();
    }

    /**
     * Open the window.
     */
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
        // Libera a imagem de fundo ao fechar
        if (backgroundImage != null && !backgroundImage.isDisposed()) {
            backgroundImage.dispose();
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(600, 400);
        shell.setText("Alterar Funcionário");

        // Carrega a imagem de fundo
        backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsFuc.jpg");
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Adiciona o PaintListener para desenhar a imagem de fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                // Desenha a imagem de fundo
                if (backgroundImage != null) {
                    e.gc.drawImage(backgroundImage, 0, 0, 
                                   backgroundImage.getBounds().width, backgroundImage.getBounds().height, 
                                   0, 0, shell.getBounds().width, shell.getBounds().height);
                }
                
            }
        });
        
        Label lblTitulo = new Label(shell, SWT.CENTER);
        lblTitulo.setAlignment(SWT.LEFT);
        lblTitulo.setText("Alterar Funcionário");
        lblTitulo.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblTitulo.setBounds(243, 10, 106, 15);

        textidFuncionario = new Text(shell, SWT.BORDER);
        textidFuncionario.setBounds(287, 97, 83, 21);

        textnomeFuncionario = new Text(shell, SWT.BORDER);
        textnomeFuncionario.setBounds(166, 195, 241, 30);

        textdepFuncionario = new Text(shell, SWT.BORDER);
        textdepFuncionario.setBounds(166, 231, 241, 30);

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        btnCancelar.setBounds(47, 299, 97, 34);

        Button btnOK = new Button(shell, SWT.NONE);
        btnOK.setText("OK");
        btnOK.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnOK.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        btnOK.setBounds(435, 299, 97, 34);
        
        lblNomeDoFuncionario = new Label(shell, SWT.NONE);
        lblNomeDoFuncionario.setBounds(43, 195, 119, 15);
        lblNomeDoFuncionario.setText("Nome do Funcionario:");
        
        lblDepartamento = new Label(shell, SWT.NONE);
        lblDepartamento.setBounds(83, 231, 79, 15);
        lblDepartamento.setText("Departamento:");
        
        lblInsiraOsNovos = new Label(shell, SWT.NONE);
        lblInsiraOsNovos.setBounds(135, 146, 116, 15);
        lblInsiraOsNovos.setText("Insira os novos dados:");
        
        lblDigiteOId = new Label(shell, SWT.NONE);
        lblDigiteOId.setBounds(47, 100, 234, 15);
        lblDigiteOId.setText("Digite o ID do funcionário que deseja alterar:");

        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Funcionarios funcionario = new Funcionarios();
                int idFuncionario = Integer.parseInt(textidFuncionario.getText());
                funcionario.setIdFuncionario(idFuncionario);
                funcionario.setNomeFuncionario(textnomeFuncionario.getText());
                funcionario.setDepFuncionario(textdepFuncionario.getText());

                funcionarioBanco.alterarFunc(funcionario); // Lembre-se de criar este método no funcionarioBanco
                
                MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                messagebox.setMessage("Funcionário alterado com sucesso");
                messagebox.open();
            }
        });

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textnomeFuncionario.setText("");
                textdepFuncionario.setText("");
                
                // Se desejar, também pode fechar a janela ao cancelar:
                shell.dispose(); // Fechar a janela
            }
        });
        
    }

    // Função para criar e gerenciar os recursos como fontes
    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
