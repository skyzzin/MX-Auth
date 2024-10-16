# MX-Auth - Plugin de Autenticação para Minecraft Spigot
## [Click Aqui Para Baixar](https://github.com/skyzzin/MX-Auth/raw/refs/heads/master/MX-AUTH.jar)
MX-Auth é um plugin de autenticação para servidores Minecraft Spigot que permite proteger sua conta com uma senha personalizada. Ele é ideal para servidores offline, onde a verificação de autenticação é essencial para garantir que os jogadores não utilizem nomes de outros jogadores.

## Funcionalidades

- Registro de novos jogadores com senha.
- Login seguro para jogadores registrados.
- Bloqueio de movimento até que o jogador esteja autenticado.
- Teletransporte temporário para o alto (Y=999) enquanto o jogador não se autentica.
- Mensagens personalizadas para ajudar os jogadores no processo de login e registro.

## Imagens do Processo

### 1. Solicitação de Registro
Se o jogador ainda não tiver uma conta, ele será solicitado a criar uma com o comando `/register <senha> <senha>`.

![Solicitação de Registro](https://github.com/skyzzin/MX-Auth/blob/master/readme_files/1.png)

### 2.  Solicitação de Login
Após o registro, o jogador será notificado de que sua conta foi criada com sucesso.

![Registro Bem-Sucedido](https://github.com/skyzzin/MX-Auth/blob/master/readme_files/2.png)

### 3. Login Bem-Sucedido
Após fazer login corretamente, o jogador será informado de que está autenticado e poderá se mover livremente.

![Login Bem-Sucedido](https://github.com/skyzzin/MX-Auth/blob/master/readme_files/4.png)

### 4. Login Mal-Sucedido
Caso o jogador coloque os dados errados vera uma tela de falha

![Jogador Autenticado](https://github.com/skyzzin/MX-Auth/blob/master/readme_files/6.png)



## Comandos

- `/register <senha> <senha>`: Registra um novo jogador com a senha especificada.
- `/login <senha>`: Autentica o jogador com a senha registrada.

## Instalação

1. Baixe o arquivo .jar do plugin MX-Auth.
2. Coloque o arquivo na pasta `plugins` do seu servidor Spigot.
3. Reinicie o servidor.
4. O plugin estará ativo e pronto para uso.

## Configuração

O plugin não requer configuração inicial, mas opções avançadas podem ser ajustadas no arquivo de configuração `config.yml` gerado na pasta de plugins.

## Requisitos

- Servidor Minecraft Spigot 1.21 ou superior.
- Java 21
- ## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues no repositório oficial.
