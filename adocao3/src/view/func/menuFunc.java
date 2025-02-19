package view.func;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class menuFunc {

    protected Shell shell;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage;

    public static void main(String[] args) {
        try {
            menuFunc window = new menuFunc();
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
        if (backgroundImage != null && !backgroundImage.isDisposed()) {
            backgroundImage.dispose();
        }
    }

    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(600, 400);
        shell.setText("Menu Funcionário");

        // Carrega a imagem de fundo
        backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsFuc.jpg");
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Adiciona o PaintListener para desenhar a imagem de fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                if (backgroundImage != null) {
                    e.gc.drawImage(backgroundImage, 0, 0,
                                   backgroundImage.getBounds().width, backgroundImage.getBounds().height,
                                   0, 0, shell.getBounds().width, shell.getBounds().height);
                }
            }
        });

        // Botões
        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(69, 82, 165, 50);
        btnInserir.setText("Inserir Funcionário");
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); // Verde
        btnInserir.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));

        Button btnAlterar = new Button(shell, SWT.NONE);
        btnAlterar.setText("Alterar Funcionário");
        btnAlterar.setBounds(69, 138, 165, 50);
        btnAlterar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); // Laranja
        btnAlterar.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setText("Consultar Funcionário");
        btnConsultar.setBounds(352, 82, 165, 50);
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); // Azul
        btnConsultar.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));

        Button btnListar = new Button(shell, SWT.NONE);
        btnListar.setText("Listar Funcionários");
        btnListar.setBounds(352, 138, 165, 50);
        btnListar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); // Vermelho
        btnListar.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));

        Button btnVoltar = new Button(shell, SWT.NONE);
        btnVoltar.setBounds(10, 326, 75, 25);
        btnVoltar.setText("Voltar");
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED)); // Tomate
        btnVoltar.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));

        // Título
        Label lblMenuFuncionrio = new Label(shell, SWT.NONE);
        lblMenuFuncionrio.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblMenuFuncionrio.setBounds(209, 10, 165, 25);
        lblMenuFuncionrio.setText("Menu Funcionário");

        // Listeners para os botões
        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inserirFunc inserirFunc = new inserirFunc();
                inserirFunc.open();
            }
        });

        btnAlterar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                alterarFunc alterarFunc = new alterarFunc();
                alterarFunc.open();
            }
        });

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                consultarFunc consultarFunc = new consultarFunc();
                consultarFunc.open();
            }
        });

        btnListar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listarFuncs listarFuncs = new listarFuncs();
                listarFuncs.open();
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
