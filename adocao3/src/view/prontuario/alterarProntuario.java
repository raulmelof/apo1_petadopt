package view.prontuario;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.prontuarioBanco;

public class alterarProntuario {
    protected Shell shell;
    private Text textCodProntuario;
    private Text textIdadeAnimal;
    private Text textPesoAnimal;
    private prontuarioBanco prontuarioBanco;
    private LocalResourceManager localResourceManager;

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        try {
            alterarProntuario window = new alterarProntuario();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for initializing dependencies.
     */
    public alterarProntuario() {
        prontuarioBanco = new prontuarioBanco();
    }

    /**
     * Opens the window.
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
     * Creates the contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(500, 400);
        shell.setText("Adote um Pet - Alterar Prontuário");

        // Set background image
        String imagePath = "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsPront.jpg";
        Image backgroundImage = new Image(Display.getCurrent(), imagePath);
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Title label
        Label lblTitulo = new Label(shell, SWT.CENTER);
        lblTitulo.setText("Alterar Prontuário");
        lblTitulo.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblTitulo.setBounds(187, 10, 130, 25);

        // Form labels and text fields
        createFormComponents();

        // Buttons
        createButtons();
    }

    /**
     * Creates the form components (labels and text fields).
     */
    private void createFormComponents() {
        Label lblCodProntuario = new Label(shell, SWT.NONE);
        lblCodProntuario.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblCodProntuario.setText("Código do Prontuário:");
        lblCodProntuario.setBounds(66, 80, 150, 15);

        textCodProntuario = new Text(shell, SWT.BORDER);
        textCodProntuario.setBounds(220, 77, 200, 25);

        Label lblIdadeAnimal = new Label(shell, SWT.NONE);
        lblIdadeAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblIdadeAnimal.setText("Nova Idade do Animal:");
        lblIdadeAnimal.setBounds(66, 120, 150, 15);

        textIdadeAnimal = new Text(shell, SWT.BORDER);
        textIdadeAnimal.setBounds(220, 117, 200, 25);

        Label lblPesoAnimal = new Label(shell, SWT.NONE);
        lblPesoAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblPesoAnimal.setText("Novo Peso do Animal:");
        lblPesoAnimal.setBounds(66, 160, 150, 15);

        textPesoAnimal = new Text(shell, SWT.BORDER);
        textPesoAnimal.setBounds(220, 157, 200, 25);
    }

    /**
     * Creates the buttons (Alterar and Cancelar).
     */
    private void createButtons() {
        Label lblMensagem = new Label(shell, SWT.NONE);
        lblMensagem.setBounds(66, 200, 360, 40);

        Button btnAlterar = new Button(shell, SWT.PUSH);
        btnAlterar.setText("Alterar");
        btnAlterar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAlterar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnAlterar.setBounds(364, 303, 90, 30);

        btnAlterar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handleAlterar(lblMensagem);
            }
        });

        Button btnCancelar = new Button(shell, SWT.PUSH);
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(35, 303, 90, 30);

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
    }

    /**
     * Handles the "Alterar" button click event.
     */
    private void handleAlterar(Label lblMensagem) {
        try {
            int codProntuario = Integer.parseInt(textCodProntuario.getText());
            int idadeAnimal = Integer.parseInt(textIdadeAnimal.getText());
            float pesoAnimal = Float.parseFloat(textPesoAnimal.getText());

            boolean sucesso = prontuarioBanco.alterarProntuario(codProntuario, idadeAnimal, pesoAnimal);

            lblMensagem.setText(sucesso ? "Prontuário alterado com sucesso!" : "Erro ao alterar o prontuário. Verifique os dados.");
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Erro: Dados inválidos. Certifique-se de que todos os campos estão preenchidos corretamente.");
        } catch (Exception ex) {
            lblMensagem.setText("Erro ao alterar prontuário: " + ex.getMessage());
        }
    }

    /**
     * Creates the resource manager for managing resources like colors and fonts.
     */
    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
