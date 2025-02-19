package view.adocao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.RGB;

public class menuAdocao {

    protected Shell shell;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage; // Variável para armazenar a imagem de fundo

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            menuAdocao window = new menuAdocao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        shell.setSize(500, 376);
        shell.setText("Menu Adoção");
        
        shell.setMinimumSize(500, 376);
        shell.setMaximumSize(500, 376);

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

        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(163, 107, 161, 50);
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnInserir.setText("Inserir Adoção");

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(163, 162, 161, 50);
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnConsultar.setText("Consultar Adoção");

        Button btnVoltar = new Button(shell, SWT.NONE);
        btnVoltar.setBounds(9, 289, 75, 25);
        btnVoltar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnVoltar.setText("Voltar");

        Label lblMenuAdoo = new Label(shell, SWT.NONE);
        lblMenuAdoo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblMenuAdoo.setBounds(171, 13, 129, 26);
        lblMenuAdoo.setText("Menu Adoção");

        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inserirAdocao inserirAdocao = new inserirAdocao();
                inserirAdocao.open();
            }
        });

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                consultarAdocao consultarAdocao = new consultarAdocao();
                consultarAdocao.open();
            }
        });

        btnVoltar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
