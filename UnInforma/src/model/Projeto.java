/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sabrina Winckler
 */
public class Projeto {
    //Um exemplo do que tem que ser feito da parte da api;
    

//    /**;
//     * Construtor para a classe.
//     */
//    public Feriados() {
//        super(true);
//    }
//
//    /**
//     * Método para iniciar a chamada dessa classe como comando.
//     * @param params Recebe uma lista com comandos solicitados.
//     * @return Resposta final de execução do comando dessa classe.
//     */
//    @Override
//    public String run(List<String> params) {
//        if(params.isEmpty()) {
//            throw new InputMismatchException("Erro de uso. Esperado: " + this.usage());
//        }
//
//        String month = params.get(0);
//
//        try {
//            APIHelper api = new APIHelper("https://8nwxe02dt5.execute-api.sa-east-1.amazonaws.com/rsd/feriados");
//            Map<String, Object> values = new HashMap<String, Object>();
//            values.put("month", month);
//            String response = api.post(values);
//            List<Feriado> feriados = this.jsonToMap(response);
//            return this.prepareResponse(feriados);
//        } catch(MismatchedInputException e) {
//            return "Mês informado inválido";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return e.getMessage();
//        }
//    }
//
//    /**
//     * Método para preparar a resposta final da requisição dos docentes.
//     * @param feriados Contém todos os resultados de docentes existentes.
//     * @return Resultado final dos docentes encontradoss.
//     */
//    private String prepareResponse(List<Feriado> feriados) {
//        if(feriados.isEmpty()){
//            return "Este mês não possui feriado";
//        }
//        String formatted = "";
//
//        for (Feriado feriado : feriados) {
//            formatted += feriado.data_ref + " - " + feriado.nome + " - Feriado " + feriado.tipo + "\n";
//        }
//
//        return formatted;
//    }
//
//    /**
//     * Método para retornar informações da classe atual que está sendo chamada.
//     * @return Conteúdo com informações do comando dessa classe. 
//     */
//	@Override
//    public String usage() {
//        return super.usage() + " <mes>\t- Retorna todos os feriados do a partir do mês informado.";
//    }
//
//    /**
//     * Método para fazer conversão do formato Json para um Map.
//     * @param json Recebe uma String com os dados do Json para conversão.
//     * @return Lista dos Feriados de forma convertida.
//     * @throws JsonParseException  Tipo de exceção para problemas de análise, usado quando conteúdo 
//     *         que não está em conformidade com a sintaxe JSON conforme especificação é encontrado.
//     * @throws JsonMappingException Exceção marcada usada para sinalizar problemas fatais com 
//     *         mapeamento de conteúdo.
//     * @throws IOException Sinaliza que ocorreu uma exceção de I/O de algum tipo de falha ou interrupção.
//     */
//    private List<Feriado> jsonToMap(String json) throws JsonParseException, JsonMappingException, IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<Feriado> map = mapper.readValue(json, new TypeReference<ArrayList<Feriado>>(){});
//        return map;
//    }
//
//}
    
}
