package src.view.animal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.RGB;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import banco.animalBanco;
import model.Animal;

public class consultarAnimal {

    protected Shell shell;
    private Text textidAnimal;
    private Text textResultado;
    private animalBanco animalBanco;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            consultarAnimal window = new consultarAnimal();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public consultarAnimal() {
        animalBanco = new animalBanco(); // Inicializa o objeto para interagir com o banco
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
        shell.setSize(600, 400);
        shell.setText("Consultar Animal");

        // Configura o fundo do Shell com a imagem
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petAd.jpg");
        shell.setBackgroundImage(backgroundImage);

        // Label para título
        Label lblTitulo = new Label(shell, SWT.NONE);
        lblTitulo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.BOLD)));
        lblTitulo.setBounds(122, 10, 343, 20);
        lblTitulo.setText("Consultar Informações de um Animal");
        lblTitulo.setBackground(shell.getBackground());

        // Label para ID do animal
        Label lblIdAnimal = new Label(shell, SWT.NONE);
        lblIdAnimal.setText("Digite o ID do Animal:");
        lblIdAnimal.setBounds(72, 58, 120, 15);
        lblIdAnimal.setBackground(shell.getBackground());

        // Campo de texto para o ID do Animal
        textidAnimal = new Text(shell, SWT.BORDER);
        textidAnimal.setBounds(220, 55, 150, 25);

        // Label para resultado
        Label lblResultado = new Label(shell, SWT.NONE);
        lblResultado.setText("Resultado:");
        lblResultado.setBounds(72, 96, 55, 15);
        lblResultado.setBackground(shell.getBackground());

        // Campo de texto para exibir o resultado
        textResultado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        textResultado.setBounds(92, 131, 400, 150);

        // Botão de consulta
        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(435, 300, 120, 34);
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnConsultar.setText("Consultar");

        // Botão de cancelamento
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setBounds(36, 300, 120, 34);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setText("Cancelar");

        // Evento para o botão "Consultar"
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int idAnimal = Integer.parseInt(textidAnimal.getText());

                    // Criar o objeto animal para realizar a consulta
                    Animal animal = new Animal();
                    animal.setIdAnimal(idAnimal);

                    // Chamar o método consultarAnimal do animalBanco
                    Animal resultado = animalBanco.consultarAnimal(animal);

                    // Exibir os dados retornados na interface
                    if (resultado != null) {
                        textResultado.setText("ID: " + resultado.getIdAnimal() + "\n" +
                                              "Nome: " + resultado.getNomeAnimal() + "\n" +
                                              "Sexo: " + resultado.getSexoAnimal() + "\n" +
                                              "Raça: " + resultado.getRacaAnimal() + "\n" +
                                              "Status: " + resultado.getStatus().name());
                    } else {
                        textResultado.setText("Animal não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    textResultado.setText("Erro: ID inválido.");
                } catch (Exception ex) {
                    textResultado.setText("Erro ao consultar o animal: " + ex.getMessage());
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textidAnimal.setText("");
                textResultado.setText("");

                // Fecha a janela ao cancelar, se necessário
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
