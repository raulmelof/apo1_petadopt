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
import org.eclipse.swt.widgets.MessageBox;

import banco.animalBanco;

public class AlterarDisponivel {

    protected Shell shell;
    private Text textIdAnimal;
    private animalBanco animalBanco;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            AlterarDisponivel window = new AlterarDisponivel();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AlterarDisponivel() {
        animalBanco = new animalBanco(); // Instancia a classe do banco de dados para interagir com o banco
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
        shell.setSize(450, 300);
        shell.setText("Alterar Status do Animal para DISPONÍVEL");

        // Configura o fundo do Shell com a imagem
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petAd.jpg");
        shell.setBackgroundImage(backgroundImage);

        // Label para título
        Label lblTitulo = new Label(shell, SWT.NONE);
        lblTitulo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.BOLD)));
        lblTitulo.setBounds(50, 10, 350, 30);
        lblTitulo.setText("Alterar Status do Animal para disponível");
        lblTitulo.setBackground(shell.getBackground());

        // Label para ID do animal
        Label lblIdAnimal = new Label(shell, SWT.NONE);
        lblIdAnimal.setText("Digite o ID do Animal:");
        lblIdAnimal.setBounds(50, 60, 150, 20);
        lblIdAnimal.setBackground(shell.getBackground());

        // Campo de texto para o ID do Animal
        textIdAnimal = new Text(shell, SWT.BORDER);
        textIdAnimal.setBounds(210, 55, 150, 25);

        // Botão de alteração
        Button btnAlterar = new Button(shell, SWT.NONE);
        btnAlterar.setBounds(240, 179, 120, 34);
        btnAlterar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAlterar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnAlterar.setText("Alterar Status");

        // Botão de cancelamento
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setBounds(80, 179, 120, 34);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setText("Cancelar");

        // Evento para o botão "Alterar Status"
        btnAlterar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String idAnimalStr = textIdAnimal.getText();

                    if (idAnimalStr == null || idAnimalStr.isEmpty()) {
                        throw new IllegalArgumentException("ID do animal não pode ser vazio.");
                    }

                    Integer idAnimal = Integer.parseInt(idAnimalStr);

                    // Verifica o prontuário do animal e se ele atende os critérios
                    if (animalBanco.verificaProntuario(idAnimal)) {
                        // Altera o status do animal para DISPONÍVEL
                        animalBanco.alterarDisponivel(idAnimal);

                        // Exibe mensagem de sucesso
                        MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                        messagebox.setMessage("Animal com ID " + idAnimal + " alterado para DISPONÍVEL com sucesso.");
                        messagebox.open();
                    } else {
                        // Exibe mensagem de erro caso o prontuário não atenda os critérios
                        MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                        messagebox.setMessage("O prontuário do animal com ID " + idAnimal + " não atende aos requisitos.");
                        messagebox.open();
                    }

                } catch (IllegalArgumentException ex) {
                    // Mensagem de erro caso o ID seja inválido
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro: " + ex.getMessage());
                    messagebox.open();
                } catch (Exception ex) {
                    // Exibe mensagem de erro em caso de falha no processo
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao alterar o status: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa o campo de texto e fecha a janela
                textIdAnimal.setText("");
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
