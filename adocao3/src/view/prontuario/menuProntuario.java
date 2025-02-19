package view.prontuario;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class menuProntuario {
    private LocalResourceManager localResourceManager;
    protected Shell shell;

    public static void main(String[] args) {
        try {
            menuProntuario window = new menuProntuario();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        shell.setSize(500, 400);
        shell.setText("Menu Prontuário");

        // Define a imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsPront.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Título
        Label lblMenuProntuario = new Label(shell, SWT.CENTER);
        lblMenuProntuario.setText("Menu Prontuário");
        lblMenuProntuario.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblMenuProntuario.setBounds(150, 40, 200, 20);

        // Botão Inserir Prontuário
        Button btnInserir = new Button(shell, SWT.PUSH);
        btnInserir.setText("Inserir Prontuário");
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnInserir.setBounds(150, 100, 200, 50);

        // Botão Consultar Prontuário
        Button btnConsultar = new Button(shell, SWT.PUSH);
        btnConsultar.setText("Consultar Prontuário");
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnConsultar.setBounds(150, 170, 200, 50);

        // Botão Voltar
        Button btnVoltar = new Button(shell, SWT.PUSH);
        btnVoltar.setText("Voltar");
        btnVoltar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnVoltar.setBounds(50, 300, 100, 30);

        // Evento para o botão "Inserir Prontuário"
        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inserirProntuario inserirProntuario = new inserirProntuario();
                inserirProntuario.open();
            }
        });

        // Evento para o botão "Consultar Prontuário"
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                consultarProntuario consultarProntuario = new consultarProntuario();
                consultarProntuario.open();
            }
        });

        // Evento para o botão "Voltar"
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
