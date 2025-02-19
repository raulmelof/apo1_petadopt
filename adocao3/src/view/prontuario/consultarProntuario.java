package view.prontuario;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.prontuarioBanco;
import model.Prontuario;

import java.util.List;

public class consultarProntuario {
    protected Shell shell;
    private Text textCodProntuario;
    private Text textResultado;
    private prontuarioBanco prontuarioBanco;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            consultarProntuario window = new consultarProntuario();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public consultarProntuario() {
        prontuarioBanco = new prontuarioBanco(); // Inicializa o banco
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
        shell.setText("Adote um Pet - Consulta de Prontuário");

        // Configurar imagem de fundo
        String imagePath = "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsPront.jpg";
        Image backgroundImage = new Image(Display.getCurrent(), imagePath);
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Criar elementos da interface
        createFormComponents();
        createButtons();
    }

    private void createFormComponents() {
        // Título
        Label lblTitulo = new Label(shell, SWT.CENTER);
        lblTitulo.setText("Consulta de Prontuário");
        lblTitulo.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblTitulo.setBounds(169, 10, 180, 25);

        // Campo para Código do Prontuário
        Label lblCodProntuario = new Label(shell, SWT.NONE);
        lblCodProntuario.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblCodProntuario.setText("Código do Prontuário:");
        lblCodProntuario.setBounds(55, 80, 150, 15);

        textCodProntuario = new Text(shell, SWT.BORDER);
        textCodProntuario.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCodProntuario.setBounds(220, 77, 200, 25);

        // Resultado
        Label lblResultado = new Label(shell, SWT.NONE);
        lblResultado.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblResultado.setText("Resultado:");
        lblResultado.setBounds(55, 120, 55, 15);

        textResultado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        textResultado.setBackground(localResourceManager.createColor(new RGB(255, 248, 220)));
        textResultado.setBounds(55, 140, 400, 150);
    }

    private void createButtons() {
        // Botão "Consultar"
        Button btnConsultar = new Button(shell, SWT.PUSH);
        btnConsultar.setText("Consultar");
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnConsultar.setBounds(364, 303, 90, 30);

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handleConsultar();
            }
        });

        // Botão "Cancelar"
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

    private void handleConsultar() {
        try {
            int codProntuario = Integer.parseInt(textCodProntuario.getText());
            Prontuario prontuario = new Prontuario();
            prontuario.setCodProntuario(codProntuario);

            Prontuario resultado = prontuarioBanco.consultarProntuario(prontuario);

            if (resultado != null) {
                List<String> nomesDoencas = resultado.getNomesDoencas();
                String doencas = (nomesDoencas != null && !nomesDoencas.isEmpty())
                        ? String.join(", ", nomesDoencas)
                        : "Nenhuma doença registrada";

                textResultado.setText("Código: " + resultado.getCodProntuario() + "\n" +
                                      "Idade: " + resultado.getIdadeAnimal() + "\n" +
                                      "Peso: " + resultado.getPesoAnimal() + "\n" +
                                      "Higiene: " + resultado.getHigieneAnimal() + "\n" +
                                      "Tipo: " + resultado.getTipoAnimal() + "\n" +
                                      "Doenças: " + doencas);
            } else {
                textResultado.setText("Prontuário não encontrado.");
            }
        } catch (NumberFormatException ex) {
            textResultado.setText("Erro: Código inválido.");
        } catch (Exception ex) {
            textResultado.setText("Erro ao consultar o prontuário: " + ex.getMessage());
        }
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
