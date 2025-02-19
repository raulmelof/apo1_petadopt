package view.func;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.funcionarioBanco;
import model.Funcionarios;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Label;


public class consultarFunc {
    protected Shell shell;
    private Text textidFuncionario;
    private Text textResultado;
    private funcionarioBanco funcionarioBanco;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage;
    private Label lblResultado;
    private Label lblDigiteOId;


    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            consultarFunc window = new consultarFunc();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public consultarFunc() {
        funcionarioBanco = new funcionarioBanco(); // Inicializa o objeto para interagir com o banco
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
        shell.setText("Consultar Funcionário");

        // Carrega a imagem de fundo
        backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsFuc.jpg");
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

        textidFuncionario = new Text(shell, SWT.BORDER);
        textidFuncionario.setBounds(278, 67, 83, 21);

        textResultado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        textResultado.setBounds(81, 152, 410, 120);

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnConsultar.setBounds(442, 300, 97, 34);
        btnConsultar.setText("Consultar");

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(30, 300, 97, 34);
        btnCancelar.setText("Cancelar");
        
        lblResultado = new Label(shell, SWT.NONE);
        lblResultado.setBounds(55, 116, 55, 15);
        lblResultado.setText("Resultado:");
        
        lblDigiteOId = new Label(shell, SWT.NONE);
        lblDigiteOId.setBounds(55, 70, 217, 15);
        lblDigiteOId.setText("Digite o ID do funcionário para consultar:");
        
        Label lblTitulo = new Label(shell, SWT.CENTER);
        lblTitulo.setAlignment(SWT.LEFT);
        lblTitulo.setText("Consultar Funcionário");
        lblTitulo.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblTitulo.setBounds(243, 10, 119, 15);
        

        // Tornando o fundo do título transparente
        lblTitulo.setBackground(null);  // Isso fará o fundo do título transparente


        // Evento para o botão "Consultar"
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int idFuncionario = Integer.parseInt(textidFuncionario.getText());

                    // Criar o objeto Funcionarios para realizar a consulta
                    Funcionarios funcionario = new Funcionarios();
                    funcionario.setIdFuncionario(idFuncionario);

                    // Chamar o método consultarFuncionario do funcionarioBanco
                    Funcionarios resultado = funcionarioBanco.consultarFunc(funcionario);

                    // Exibir os dados retornados na interface
                    if (resultado != null) {
                        textResultado.setText("ID: " + resultado.getIdFuncionario() + "\n" +
                                              "Nome: " + resultado.getNomeFuncionario() + "\n" +
                                              "Departamento: " + resultado.getDepFuncionario());
                    } else {
                        textResultado.setText("Funcionário não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    textResultado.setText("Erro: ID inválido.");
                } catch (Exception ex) {
                    textResultado.setText("Erro ao consultar o funcionário: " + ex.getMessage());
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textidFuncionario.setText("");
                textResultado.setText("");

                // Se necessário, pode fechar a janela ao cancelar
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
