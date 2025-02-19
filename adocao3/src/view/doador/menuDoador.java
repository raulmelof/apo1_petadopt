package view.doador;

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

public class menuDoador {

    protected Shell shell;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            menuDoador window = new menuDoador();
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
        shell.setSize(600, 450);
        shell.setText("Menu Doador");

        // Definir a imagem de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsCl2.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE); // Permite herança de fundo para widgets filhos

        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(208, 123, 165, 50);
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnInserir.setText("Cadastrar Doador");

        Button btnAlterar = new Button(shell, SWT.NONE);
        btnAlterar.setText("Alterar Doador");
        btnAlterar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAlterar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnAlterar.setBounds(75, 211, 165, 50);

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setText("Consultar Doador");
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnConsultar.setBounds(75, 267, 165, 50);

        Button btnListar = new Button(shell, SWT.NONE);
        btnListar.setText("Listar Doadores");
        btnListar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnListar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnListar.setBounds(342, 211, 165, 50);

        Button btnRemover = new Button(shell, SWT.NONE);
        btnRemover.setText("Remover Doador");
        btnRemover.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnRemover.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnRemover.setBounds(342, 267, 165, 50);

        Button btnVoltar = new Button(shell, SWT.NONE);
        btnVoltar.setBounds(27, 360, 75, 25);
        btnVoltar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnVoltar.setText("Voltar");

        Label lblMenuDoador = new Label(shell, SWT.NONE);
        lblMenuDoador.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei Light", 14, SWT.NORMAL)));
        lblMenuDoador.setBounds(232, 35, 122, 25);
        lblMenuDoador.setText("Menu Doador");

        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inserirDoador inserirDoador = new inserirDoador();
                inserirDoador.open();
            }
        });

        btnAlterar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                alterarDoador alterarDoador = new alterarDoador();
                alterarDoador.open();
            }
        });

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                consultarDoador consultarDoador = new consultarDoador();
                consultarDoador.open();
            }
        });

        btnListar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listarDoadores listarDoadores = new listarDoadores();
                listarDoadores.open();
            }
        });

        btnRemover.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                removerDoador removerDoador = new removerDoador();
                removerDoador.open();
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
