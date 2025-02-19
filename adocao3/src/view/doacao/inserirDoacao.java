package view.doacao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import banco.doacaoBanco;
import model.Doacao;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import java.time.LocalDate;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.FontDescriptor;

public class inserirDoacao {

    protected Shell shell;
    private LocalResourceManager localResourceManager;
    private Text textLocalDoacao;
    private doacaoBanco doacaoBanco;
    private Label lblLocalDaDoao;
    private Label lblDataDaDoao;
    private Label lblNewLabel;
    private Label lblCadastrarDoao;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            inserirDoacao window = new inserirDoacao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirDoacao() {
        doacaoBanco = new doacaoBanco(); // Inicializa o objeto para interagir com o banco
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
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(600, 450);
        shell.setText("Inserir Doação");

        // Define a imagem de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petDo.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE); // Permite herança de fundo para widgets filhos

        textLocalDoacao = new Text(shell, SWT.BORDER);
        textLocalDoacao.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textLocalDoacao.setForeground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(0, 0, 0))));
        textLocalDoacao.setBounds(201, 224, 225, 30);

        Button btnOK = new Button(shell, SWT.NONE);
        btnOK.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnOK.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnOK.setBounds(411, 347, 89, 34);
        btnOK.setText("OK");

        DateTime dateDataDoacao = new DateTime(shell, SWT.BORDER);
        dateDataDoacao.setBounds(201, 180, 225, 34);

        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    LocalDate data = LocalDate.of(dateDataDoacao.getYear(),
                            dateDataDoacao.getMonth() + 1, // Ajuste necessário para o DateTime
                            dateDataDoacao.getDay());

                    String localDoacao = textLocalDoacao.getText();

                    if (localDoacao.isEmpty()) {
                        throw new IllegalArgumentException("O local da doação não pode estar vazio.");
                    }

                    Doacao doacao = new Doacao(data, localDoacao);

                    doacaoBanco.inserirDoacao(doacao);

                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Doação inserida com sucesso.");
                    messagebox.open();

                } catch (IllegalArgumentException ex) {
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro: " + ex.getMessage());
                    messagebox.open();
                } catch (Exception ex) {
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao inserir a doação: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(94, 347, 89, 34);
        btnCancelar.setText("Cancelar");
        
        lblLocalDaDoao = new Label(shell, SWT.NONE);
        lblLocalDaDoao.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblLocalDaDoao.setBounds(109, 224, 89, 15);
        lblLocalDaDoao.setText("Local da doaçâo:");
        
        lblDataDaDoao = new Label(shell, SWT.BORDER);
        lblDataDaDoao.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblDataDaDoao.setBounds(113, 182, 89, 15);
        lblDataDaDoao.setText("Data da doaçâo:");
        
        lblNewLabel = new Label(shell, SWT.SHADOW_NONE);
        lblNewLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblNewLabel.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
        lblNewLabel.setBounds(156, 142, 186, 15);
        lblNewLabel.setText("(Preencha os campos obrigatórios)");
        
        lblCadastrarDoao = new Label(shell, SWT.NONE);
        lblCadastrarDoao.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblCadastrarDoao.setBounds(212, 54, 158, 25);
        lblCadastrarDoao.setText("Cadastrar Doaçâo");

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textLocalDoacao.setText("");
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
