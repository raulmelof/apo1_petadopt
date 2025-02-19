package view.invest;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.investigadorBanco;
import model.Investigador;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.RGB;

public class inserirAut {

    protected Shell shell;
    private Text txtIdFuncionario;
    private Text txtAutInvestigador;
    private investigadorBanco investigadorBanco;
    private Label lblInserirAutorizaoDo;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            inserirAut window = new inserirAut();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirAut() {
        investigadorBanco = new investigadorBanco(); // Instancia o banco de dados
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
        shell.setSize(500, 350);
        shell.setText("Inserir Autorização do Investigador");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsInvest.jpg");

        // Listener para desenhar a imagem como fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(backgroundImage, 0, 0,
                    backgroundImage.getBounds().width, backgroundImage.getBounds().height,
                    0, 0, shell.getSize().x, shell.getSize().y);
            }
        });

        // Labels e campos de entrada de dados
        Label lblIdFuncionario = new Label(shell, SWT.NONE);
        lblIdFuncionario.setText("ID do Funcionário:");
        lblIdFuncionario.setBounds(150, 96, 97, 20);

        txtIdFuncionario = new Text(shell, SWT.BORDER);
        txtIdFuncionario.setBounds(150, 122, 168, 30);

        Label lblAutInvestigador = new Label(shell, SWT.NONE);
        lblAutInvestigador.setText("Autorização do Investigador:");
        lblAutInvestigador.setBounds(150, 158, 151, 20);

        txtAutInvestigador = new Text(shell, SWT.BORDER);
        txtAutInvestigador.setBounds(150, 184, 168, 30);

        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(150, 236, 168, 30);
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnInserir.setText("Inserir Autorização");

        lblInserirAutorizaoDo = new Label(shell, SWT.NONE);
        lblInserirAutorizaoDo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblInserirAutorizaoDo.setBounds(71, 21, 319, 30);
        lblInserirAutorizaoDo.setText("Inserir Autorização do Investigador");

        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    Integer idFuncionario = Integer.parseInt(txtIdFuncionario.getText());
                    String autInvestigador = txtAutInvestigador.getText();

                    Investigador investigador = new Investigador(idFuncionario, autInvestigador);
                    // Chama o método para inserir a autorização
                    investigadorBanco.inserirAut(investigador);

                    // Mensagem de sucesso
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Autorização inserida com sucesso!");
                    messagebox.open();

                } catch (Exception ex) {
                    // Mensagem de erro em caso de falha
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao inserir a autorização: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Listener para liberar recursos da imagem
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
