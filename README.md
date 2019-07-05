UnInforma

Bot Server - Engenharia de Software

OBJETIVOS:
Este projeto tem como objetivo implementar um componente servidor para um sistema simplificado de Bot. Em geral, este servidor deverá responder questões básicas sobre o curso de Engenharia de Software a partir de perguntas enviadas pelos usuários.

Um usuário poderá acessar este bot através de um Cliente para enviar e receber perguntas para/do Servidor. Este servidor deverá:

receber e/ou aguardar mensagens;
processar mensagens, e;
responder adequadamente para o Cliente.

TIPOS DE COMANDOS:
Este servidor tem 3 tipos de comandos. Cada comando possui uma complexidade distinta (básico, intermediário ou avançado), conforme apresentada a seguir.

Nivel básico: Não requer consulta externa, apenas local e não requer passagem de parâmetros.
Nivel intermediário: Requer consulta externa e não exige passagem de parâmetros.
Nivel avançado: Requer consulta externa e passagem de parâmetros.
Uso
O usuário atráves do Cliente deverá informar um comando iniciando com “\” (backslash) para que o servidor possa identificar cada caso.

Caso o usuário envie uma mensagem sem o caractere “\”, este servidor irá apenas repetir a mensagem para o cliente, sem efetuar nenhuma ação adicional.


**PORTAS E IPS**
O Servidor de Informação está em localhost:8080
O IP do Servidor Líder está definido de acordo com o endereço IP do localhost
Os IPs do grupo de Servidores estão definidos de acordo com a rede a qual a máquina está concectada e de acordo com uma (ou mais) máquinas virtuais com IPs distintos.
As portas utilizadas nos servidores são portas públicas, neste projeto foram utilzadas as portas: 8000 e 80.

