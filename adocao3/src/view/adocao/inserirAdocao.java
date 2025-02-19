package view.adocao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import banco.adocaoBanco;
import model.Adocao;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import java.time.LocalDate;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class inserirAdocao {

    protected Shell shell;
    private LocalResourceManager localResourceManager;
    private Text textlocalAdocao;
    private adocaoBanco adocaoBanco;
    private Text textCpfCliente;
    private Label lblDataDaAdoo;
    private Label lblLocalDaAdoo;
    private Label lblCpfDoCliente;
    private Label lbldeixeEmBranco;
    private Label lblInserirAdoo;
    private Image backgroundImage; // Definir a variável para a imagem de fundo

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            inserirAdocao window = new inserirAdocao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirAdocao() {
        adocaoBanco = new adocaoBanco();  // Inicialize o objeto aqui
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
        shell.setSize(655, 500);
        shell.setText("Inserir Adoção");

        // Impedir o redimensionamento da janela usando tamanho fixo
        shell.setMinimumSize(655, 500);
        shell.setMaximumSize(655, 500);

        // Carregar a imagem de fundo
        try {
            backgroundImage = new Image(Display.getDefault(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsAdoc3.jpg");
            System.out.println("Imagem de fundo carregada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem de fundo: " + e.getMessage());
        }

        // Configurar o evento de pintura para desenhar a imagem de fundo redimensionada
        shell.addListener(SWT.Paint, event -> {
            if (backgroundImage != null && !backgroundImage.isDisposed()) {
                GC gc = event.gc;
                Rectangle shellBounds = shell.getClientArea();
                Rectangle imageBounds = backgroundImage.getBounds();

                // Redimensionar a imagem para caber na janela
                gc.drawImage(backgroundImage, 
                             0, 0, imageBounds.width, imageBounds.height, // Origem da imagem
                             0, 0, shellBounds.width, shellBounds.height // Destino redimensionado
                );
            }
        });

        textlocalAdocao = new Text(shell, SWT.BORDER);
        textlocalAdocao.setForeground(localResourceManager.create(ColorDescriptor.createFrom(new RGB(0, 0, 0))));
        textlocalAdocao.setBounds(229, 224, 225, 30);
        
        Button btnOK = new Button(shell, SWT.NONE);
        btnOK.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnOK.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnOK.setBounds(518, 399, 89, 34);
        btnOK.setText("OK");
        
        DateTime datedataAdocao = new DateTime(shell, SWT.BORDER);
        datedataAdocao.setBounds(229, 184, 225, 34);
    
        textCpfCliente = new Text(shell, SWT.BORDER);
        textCpfCliente.setBounds(229, 267, 225, 30);
        
        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    LocalDate data = LocalDate.of(
                        datedataAdocao.getYear(),
                        datedataAdocao.getMonth() + 1, // SWT usa meses no padrão 0-11
                        datedataAdocao.getDay()
                    );
                    
                    String localAdocao = textlocalAdocao.getText();
                    String cpfCliente = textCpfCliente.getText(); // Captura do CPF do cliente

                    // Criação do objeto Adocao
                    Adocao adocao = new Adocao(data, localAdocao, null);
                    adocao.setCpfCliente(cpfCliente);

                    adocaoBanco.inserirAdocao(adocao);

                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Adoção inserida com sucesso!");
                    messagebox.open();
                } catch (Exception ex) {
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao inserir adoção: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });
        
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(34, 399, 89, 34);
        btnCancelar.setText("Cancelar");
        
        lblDataDaAdoo = new Label(shell, SWT.NONE);
        lblDataDaAdoo.setBounds(134, 184, 89, 15);
        lblDataDaAdoo.setText("Data da Adoçâo:");
        
        lblLocalDaAdoo = new Label(shell, SWT.NONE);
        lblLocalDaAdoo.setBounds(134, 224, 90, 15);
        lblLocalDaAdoo.setText("Local da Adoçâo");
        
        lblCpfDoCliente = new Label(shell, SWT.NONE);
        lblCpfDoCliente.setBounds(141, 267, 82, 15);
        lblCpfDoCliente.setText("CPF do Cliente:");
        
        lbldeixeEmBranco = new Label(shell, SWT.NONE);
        lbldeixeEmBranco.setBounds(43, 282, 180, 15);
        lbldeixeEmBranco.setText("(Deixe em branco caso nâo tenha)");
        
        lblInserirAdoo = new Label(shell, SWT.NONE);
        lblInserirAdoo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblInserirAdoo.setBounds(253, 14, 130, 26);
        lblInserirAdoo.setText("Inserir Adoçâo");
        
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                datedataAdocao.setYear(0);
                datedataAdocao.setMonth(0); 
                datedataAdocao.setDay(0);

                textlocalAdocao.setText("");
                textCpfCliente.setText("");
                
                shell.dispose();
            }
        });
    }
    
    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
