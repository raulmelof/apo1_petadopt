package view.cliente;

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
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Image;

public class menuCliente {

	protected Shell shell;
	private LocalResourceManager localResourceManager;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			menuCliente window = new menuCliente();
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
		shell.setSize(550, 400);
		shell.setText("Menu Cliente");

		// Carregar a imagem de fundo
		Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsCl.jpeg");
		shell.setBackgroundImage(backgroundImage);
		shell.setBackgroundMode(SWT.INHERIT_FORCE);

		Button btnInserir = new Button(shell, SWT.NONE);
		btnInserir.setBounds(51, 126, 157, 50);
		btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
		btnInserir.setText("Inserir Cliente");
		
		Button btnAlterar = new Button(shell, SWT.NONE);
		btnAlterar.setText("Alterar Cliente");
		btnAlterar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAlterar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
		btnAlterar.setBounds(51, 182, 157, 50);
		
		Button btnConsultar = new Button(shell, SWT.NONE);
		btnConsultar.setText("ConsultarCliente");
		btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
		btnConsultar.setBounds(51, 238, 157, 50);
		
		Button btnListar = new Button(shell, SWT.NONE);
		btnListar.setText("Listar Clientes");
		btnListar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnListar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
		btnListar.setBounds(308, 126, 157, 50);
		
		Button btnRemover = new Button(shell, SWT.NONE);
		btnRemover.setText("Remover Cliente");
		btnRemover.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnRemover.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
		btnRemover.setBounds(308, 182, 157, 50);
		
		Button btnVoltar = new Button(shell, SWT.NONE);
		btnVoltar.setBounds(10, 314, 75, 25);
		btnVoltar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnVoltar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		btnVoltar.setText("Voltar");
		
		Button btnAltStatus = new Button(shell, SWT.NONE);
		btnAltStatus.setText("Alterar Status do Cliente");
		btnAltStatus.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAltStatus.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
		btnAltStatus.setBounds(308, 238, 157, 50);
		
		Label lblMenuCliente = new Label(shell, SWT.NONE);
		lblMenuCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		lblMenuCliente.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
		lblMenuCliente.setBounds(196, 42, 122, 25);
		lblMenuCliente.setText("Menu Cliente");
		
		btnInserir.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	inserirCliente inserirCliente = new inserirCliente();
	        	inserirCliente.open();
	        }   
	    });
		
		btnAlterar.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	alterarCliente alterarCliente = new alterarCliente();
	        	alterarCliente.open();
	        }   
	    });
		
		btnConsultar.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	consultarCliente consultarCliente = new consultarCliente();
	        	consultarCliente.open();
	        }   
	    });
		
		btnListar.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	listarClientes listarClientes = new listarClientes();
	        	listarClientes.open();
	        }   
	    });
		
		btnRemover.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	removerCliente removerCliente = new removerCliente();
	        	removerCliente.open();
	        }   
	    });
		
		btnVoltar.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	shell.dispose();
	        }   
	    });
		
		btnAltStatus.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	altStatusCliente altStatusCliente = new altStatusCliente();
	        	altStatusCliente.open();
	        }   
	    });
	}

	private void createResourceManager() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
	}
}
