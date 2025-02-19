package src.view.animal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.RGB;


import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;

public class menuAnimal {

    protected Shell shell;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            menuAnimal window = new menuAnimal();
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
        shell.setSize(622, 403);
        shell.setText("Menu Animal");

        // Definir imagem de fundo
        Image backgroundImage = new Image(Display.getDefault(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petAd.jpg");
        shell.setBackgroundImage(backgroundImage);

        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(113, 101, 173, 40);
        btnInserir.setText("Inserir Animal");
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnAlterar = new Button(shell, SWT.NONE);
        btnAlterar.setBounds(113, 147, 173, 40);
        btnAlterar.setText("Alterar Animal");
        btnAlterar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAlterar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(113, 193, 173, 40);
        btnConsultar.setText("Consultar Animal");
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnVoltar = new Button(shell, SWT.NONE);
        btnVoltar.setBounds(92, 317, 75, 25);
        btnVoltar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnVoltar.setText("Voltar");

        Button btnAlterarDisp = new Button(shell, SWT.NONE);
        btnAlterarDisp.setText("Alterar Disponível");
        btnAlterarDisp.setBounds(347, 101, 173, 40);
        btnAlterarDisp.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAlterarDisp.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnLiberar = new Button(shell, SWT.NONE);
		btnLiberar.setText("Finalizar Adoção");
		btnLiberar.setBounds(347, 147, 173, 40);
		btnLiberar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnLiberar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnListarDisp = new Button(shell, SWT.NONE);
        btnListarDisp.setText("Listar Animais Disponíveis");
        btnListarDisp.setBounds(347, 193, 173, 40);
        btnListarDisp.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnListarDisp.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnReserva = new Button(shell, SWT.NONE);
        btnReserva.setText("Reserva Animal");
        btnReserva.setBounds(347, 239, 173, 40);
        btnReserva.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnReserva.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnListar = new Button(shell, SWT.NONE);
        btnListar.setText("Listar Animais");
        btnListar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnListar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnListar.setBounds(113, 239, 173, 40);

        Label lblMenu = new Label(shell, SWT.NONE);
        lblMenu.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 12, SWT.NORMAL)));
        lblMenu.setBounds(288, 10, 49, 20);
        lblMenu.setText("Menu");

        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                incluirAnimal incluirAnimal = new incluirAnimal();
                incluirAnimal.open();
            }   
        });

        btnAlterar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                alterarAnimal alterarAnimal = new alterarAnimal();
                alterarAnimal.open();
            }   
        });

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                consultarAnimal consultarAnimal = new consultarAnimal();
                consultarAnimal.open();
            }   
        });

        btnAlterarDisp.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AlterarDisponivel AlterarDisponivel = new AlterarDisponivel();
                AlterarDisponivel.open();
            }   
        });

        btnVoltar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }   
        });

        btnLiberar.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	finalizarAdocao finalizarAdocao = new finalizarAdocao(shell);
	        	finalizarAdocao.open();
	        }   
	    });

        btnReserva.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                reservaAnimal reservaAnimal = new reservaAnimal();
                reservaAnimal.open();
            }   
        });

        btnListarDisp.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listarAnimaisDisp listarAnimaisDisp = new listarAnimaisDisp();
                listarAnimaisDisp.open();
            }   
        });

        btnListar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listarAnimal listarAnimal = new listarAnimal();
                listarAnimal.open();
            }   
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
    }
}
