package view.doacao;

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
import org.eclipse.swt.graphics.RGB;

public class menuDoacao {

    protected Shell shell;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            menuDoacao window = new menuDoacao();
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
        shell.setSize(607, 375);
        shell.setText("Menu Doação");

        // Define a imagem de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petDo.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE); // Permite herança de fundo para widgets filhos

        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(223, 104, 165, 50);
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnInserir.setText("Inserir Doação");

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setText("Consultar Doação");
        btnConsultar.setBounds(223, 158, 165, 50);
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnVoltar = new Button(shell, SWT.NONE);
        btnVoltar.setBounds(26, 258, 75, 25);
        btnVoltar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnVoltar.setText("Voltar");

        Label lblMenuDoao = new Label(shell, SWT.NONE);
        lblMenuDoao.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblMenuDoao.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblMenuDoao.setBounds(240, 17, 127, 28);
        lblMenuDoao.setText("Menu Doação");

        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inserirDoacao inserirDoacao = new inserirDoacao();
                inserirDoacao.open();
            }   
        });

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                consultarDoacao consultarDoacao = new consultarDoacao();
                consultarDoacao.open();
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
