package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.adocao.menuAdocao;
import src.view.animal.menuAnimal;
import view.cliente.menuCliente;
import view.doacao.menuDoacao;
import view.doador.menuDoador;
import view.doenca.MenuDoenca;
import view.func.menuFunc;
import view.prontuario.menuProntuario;
import view.vet.inserirCrmv;
import view.invest.inserirAut;
import view.vacinas.registrarVacinas;
import org.eclipse.swt.graphics.RGB;

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

public class menuView {

    protected Shell shell;
    private Image backgroundImage;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            menuView window = new menuView();
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
        shell.setSize(480, 340);
        shell.setText("Menu");

        // Carregar a imagem de fundo
        try {
            backgroundImage = new Image(Display.getDefault(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsMenu.png");
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem de fundo: " + e.getMessage());
        }

        // Adicionar listener de pintura para desenhar a imagem de fundo
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

        Button btnAdocao = new Button(shell, SWT.NONE);
        btnAdocao.setBounds(10, 67, 155, 40);
        btnAdocao.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAdocao.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnAdocao.setText("Adoção");

        Button btnDoador = new Button(shell, SWT.NONE);
        btnDoador.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnDoador.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnDoador.setBounds(299, 67, 155, 40);
        
        btnDoador.setText("Doador");

        Button btnAnimal = new Button(shell, SWT.NONE);
        btnAnimal.setBounds(10, 113, 155, 40);
        btnAnimal.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnAnimal.setText("Animal");

        Button btnCliente = new Button(shell, SWT.NONE);
        btnCliente.setBounds(10, 159, 155, 40);
        btnCliente.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnCliente.setText("Cliente");

        Button btnDoacao = new Button(shell, SWT.NONE);
        btnDoacao.setBounds(10, 205, 155, 40);
        btnDoacao.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnDoacao.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnDoacao.setText("Doação");

        Button btnDoenca = new Button(shell, SWT.NONE);
        btnDoenca.setBounds(299, 113, 155, 40);
        btnDoenca.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnDoenca.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnDoenca.setText("Doença");

        Button btnFunc = new Button(shell, SWT.NONE);
        btnFunc.setBounds(299, 159, 155, 40);
        btnFunc.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnFunc.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnFunc.setText("Funcionário");

        Button btnProntuario = new Button(shell, SWT.NONE);
        btnProntuario.setBounds(299, 205, 155, 40);
        btnProntuario.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnProntuario.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnProntuario.setText("Prontuário");

        Button btnVet = new Button(shell, SWT.NONE);
        btnVet.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        btnVet.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnVet.setText("Veterinário");
        btnVet.setBounds(10, 251, 155, 40);

        Button btnInvest = new Button(shell, SWT.NONE);
        btnInvest.setText("Investigador");
        btnInvest.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInvest.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnInvest.setBounds(299, 251, 155, 40);

        Button btnVacinas = new Button(shell, SWT.NONE);
        btnVacinas.setBounds(193, 67, 75, 40);
        btnVacinas.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVacinas.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnVacinas.setText("Vacinas");
        
        Label lblAdoteVida = new Label(shell, SWT.NONE);
        lblAdoteVida.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblAdoteVida.setBounds(178, 10, 100, 24);
        lblAdoteVida.setText("Adote Vida");

        btnAdocao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                menuAdocao menuAdocao = new menuAdocao();
                menuAdocao.open();
            }
        });

        btnAnimal.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                menuAnimal menuAnimal = new menuAnimal();
                menuAnimal.open();
            }
        });

        btnCliente.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                menuCliente menuCliente = new menuCliente();
                menuCliente.open();
            }
        });

        btnDoacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                menuDoacao menuDoacao = new menuDoacao();
                menuDoacao.open();
            }
        });

        btnDoador.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                menuDoador menuDoador = new menuDoador();
                menuDoador.open();
            }
        });

        btnDoenca.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                MenuDoenca menuDoenca = new MenuDoenca();
                menuDoenca.open();
            }
        });

        btnFunc.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                menuFunc menuFunc = new menuFunc();
                menuFunc.open();
            }
        });

        btnProntuario.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                menuProntuario menuProntuario = new menuProntuario();
                menuProntuario.open();
            }
        });

        btnVet.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inserirCrmv inserirCrmv = new inserirCrmv();
                inserirCrmv.open();
            }
        });

        btnInvest.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inserirAut inserirAut = new inserirAut();
                inserirAut.open();
            }
        });

        btnVacinas.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                registrarVacinas registrarVacinas = new registrarVacinas();
                registrarVacinas.open();
            }
        });
    }
    private void createResourceManager() {
    	localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
    }
}
