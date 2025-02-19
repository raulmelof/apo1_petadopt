package view.adocao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.adocaoBanco;
import model.Adocao;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.RGB;

public class consultarAdocao {
    protected Shell shell;
    private Text textCodAdocao;
    private Text textResultado;
    private adocaoBanco adocaoBanco;
    private Image backgroundImage;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            consultarAdocao window = new consultarAdocao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public consultarAdocao() {
        adocaoBanco = new adocaoBanco(); // Inicializa o objeto para interagir com o banco
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
       

        // Liberar recursos ao fechar a janela
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
        shell.setSize(655, 500);
        shell.setText("Consultar Adoção");
        
        shell.setMinimumSize(655, 500);
        shell.setMaximumSize(655, 500);

        // Carregar a imagem de fundo
        backgroundImage = new Image(Display.getDefault(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsAdoc3.jpg");

        // Configurar o evento de pintura para desenhar a imagem de fundo redimensionada
        shell.addListener(SWT.Paint, event -> {
            GC gc = event.gc;
            Rectangle shellBounds = shell.getClientArea();
            Rectangle imageBounds = backgroundImage.getBounds();

            // Redimensionar a imagem para caber na janela
            gc.drawImage(backgroundImage, 
                         0, 0, imageBounds.width, imageBounds.height, // Origem da imagem
                         0, 0, shellBounds.width, shellBounds.height // Destino redimensionado
            );
        });
        
                Label lblDigiteOCdigo = new Label(shell, SWT.NONE);
                lblDigiteOCdigo.setBounds(85, 106, 220, 15);
                lblDigiteOCdigo.setText("Digite o código da adoção para consultar:");
                
                        textCodAdocao = new Text(shell, SWT.BORDER);
                        textCodAdocao.setBounds(139, 127, 83, 21);
                        
                                Label lblResultado = new Label(shell, SWT.NONE);
                                lblResultado.setBounds(85, 193, 55, 15);
                                lblResultado.setText("Resultado:");
                                
                                        textResultado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
                                        textResultado.setBounds(139, 218, 410, 155);
                                        
                                                Button btnConsultar = new Button(shell, SWT.NONE);
                                                btnConsultar.setBounds(481, 406, 97, 34);
                                                btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
                                                btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
                                                btnConsultar.setText("Consultar");
                                                
                                                        Button btnCancelar = new Button(shell, SWT.NONE);
                                                        btnCancelar.setBounds(57, 406, 97, 34);
                                                        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
                                                        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
                                                        btnCancelar.setText("Cancelar");
                                                        
                                                        Label lblConsultarAdoo = new Label(shell, SWT.NONE);
                                                        lblConsultarAdoo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
                                                        lblConsultarAdoo.setBounds(237, 19, 163, 26);
                                                        lblConsultarAdoo.setText("Consultar Adoçâo");
                                                        
                                                                // Evento para o botão "Cancelar"
                                                                btnCancelar.addSelectionListener(new SelectionAdapter() {
                                                                    @Override
                                                                    public void widgetSelected(SelectionEvent e) {
                                                                        // Limpa os campos de texto
                                                                        textCodAdocao.setText("");
                                                                        textResultado.setText("");
                                                        
                                                                        // Se necessário, pode fechar a janela ao cancelar
                                                                        shell.dispose();
                                                                    }
                                                                });

        // Evento para o botão "Consultar"
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int codAdocao = Integer.parseInt(textCodAdocao.getText());

                    // Criar o objeto Adocao para realizar a consulta
                    Adocao adocao = new Adocao(null, null, codAdocao);
                    adocao.setCodAdocao(codAdocao);

                    // Chamar o método consultarAdocao do adocaoBanco
                    Adocao resultado = adocaoBanco.consultarAdocao(adocao);

                    // Exibir os dados retornados na interface
                    if (resultado != null) {
                        textResultado.setText("Código: " + resultado.getCodAdocao() + "\n" +
                                              "Data: " + resultado.getDataAdocao() + "\n" +
                                              "Local: " + resultado.getLocalAdocao() + "\n" +
                                              "CPF Cliente: " + resultado.getCpfCliente());
                    } else {
                        textResultado.setText("Adoção não encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    textResultado.setText("Erro: Código inválido.");
                } catch (Exception ex) {
                    textResultado.setText("Erro ao consultar a adoção: " + ex.getMessage());
                }
            }
        });
    }
    private void createResourceManager() {
    	localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
    }
}
