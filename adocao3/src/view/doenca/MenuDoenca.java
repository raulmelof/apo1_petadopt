package view.doenca;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.RGB;

public class MenuDoenca {

    protected Shell shell;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            MenuDoenca window = new MenuDoenca();
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
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(500, 400);
        shell.setText("Menu Doenças");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsDoen.jpg");

        // Listener para desenhar a imagem como fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(backgroundImage, 0, 0,
                    backgroundImage.getBounds().width, backgroundImage.getBounds().height,
                    0, 0, shell.getSize().x, shell.getSize().y);
            }
        });

        // Botão Inserir Doença
        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(160, 110, 165, 50);
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnInserir.setText("Inserir Doença");

        // Botão Remover Doença
        Button btnRemover = new Button(shell, SWT.NONE);
        btnRemover.setText("Remover Doença");
        btnRemover.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnRemover.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnRemover.setBounds(160, 163, 165, 50);

        // Botão Voltar
        Button btnVoltar = new Button(shell, SWT.NONE);
        btnVoltar.setBounds(10, 313, 75, 25);
        btnVoltar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnVoltar.setText("Voltar");

        // Label Menu Doenças
        Label lblMenu = new Label(shell, SWT.NONE);
        lblMenu.setAlignment(SWT.CENTER);
        lblMenu.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblMenu.setBounds(160, 34, 165, 25);
        lblMenu.setText("Menu Doenças");

        // Evento botão Inserir
        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                InserirDoenca inserirDoenca = new InserirDoenca();
                inserirDoenca.open();
            }
        });

        // Evento botão Remover
        btnRemover.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                RemoverDoenca removerDoenca = new RemoverDoenca();
                removerDoenca.open();
            }
        });

        // Evento botão Voltar
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
