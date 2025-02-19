package src.view.animal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.animalBanco;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class finalizarAdocao {
    protected Shell shell;
    private Text textCodAdocao;
    private animalBanco animalBanco;
    private LocalResourceManager localResourceManager;
    private Label lblDigiteOCdigo;
    private Label lblFinalizarAdoo;
    private Image backgroundImage; // Variável para armazenar a imagem de fundo

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            finalizarAdocao window = new finalizarAdocao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public finalizarAdocao() {
        animalBanco = new animalBanco(); // Inicializa o objeto para interagir com o banco
    }

    public finalizarAdocao(Shell shell2) {
		// TODO Auto-generated constructor stub
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
        shell.setSize(500, 310);
        shell.setText("Finalizar Adoção");

        // Carregar a imagem de fundo
        backgroundImage = new Image(Display.getDefault(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsAdoc.jpg");

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

        textCodAdocao = new Text(shell, SWT.BORDER);
        textCodAdocao.setBounds(306, 105, 83, 21);

        Button btnFinalizar = new Button(shell, SWT.NONE);
        btnFinalizar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnFinalizar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnFinalizar.setBounds(344, 217, 97, 34);
        btnFinalizar.setText("Finalizar");

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(36, 217, 97, 34);
        btnCancelar.setText("Cancelar");
        
        lblDigiteOCdigo = new Label(shell, SWT.NONE);
        lblDigiteOCdigo.setBounds(88, 108, 213, 15);
        lblDigiteOCdigo.setText("Digite o código da adoçâo para finalizar:");
        
        lblFinalizarAdoo = new Label(shell, SWT.NONE);
        lblFinalizarAdoo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblFinalizarAdoo.setBounds(171, 18, 147, 26);
        lblFinalizarAdoo.setText("Finalizar Adoçâo");

        // Evento para o botão "Finalizar"
        btnFinalizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                	Integer idAnimal = Integer.parseInt(textCodAdocao.getText());

                    // Chama o método do banco para finalizar a adoção
                    animalBanco.finalizarAdocao(idAnimal);

                    // Exibe mensagem de sucesso
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messageBox.setText("Sucesso");
                    messageBox.setMessage("Adoção do animal ID " + idAnimal + " finalizada com sucesso!");
                    messageBox.open();

                    // Fecha a janela após sucesso
                    shell.dispose();
                } catch (NumberFormatException ex) {
                    // Exibe mensagem de erro se o ID não for válido
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messageBox.setText("Erro");
                    messageBox.setMessage("ID do Animal inválido. Digite um número válido.");
                    messageBox.open();
                } catch (RuntimeException ex) {
                    // Exibe mensagem de erro em caso de falha no banco
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messageBox.setText("Erro");
                    messageBox.setMessage("Erro ao finalizar a adoção: " + ex.getMessage());
                    messageBox.open();
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textCodAdocao.setText("");

                // Se necessário, pode fechar a janela ao cancelar
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
