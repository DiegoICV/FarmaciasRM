package farmacias.de.turno.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import farmacias.de.turno.config.FarmaciasDeTurnoRestApiConfig;
import farmacias.de.turno.dto.FarmaciasDto;
import farmacias.de.turno.mapper.JsonToDtoMaping;
import farmacias.de.turno.utils.DateUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class FarmaciasRepository {

    @Autowired
    FarmaciasDeTurnoRestApiConfig configProperties;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
     ModelMapper modelMapper;

    @Autowired
    DateUtils dateUtils;

    List<JsonToDtoMaping> jsonToDtoMapings = new ArrayList<>();


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    /*
     * Bean utilizado para mappear el objeto Json obtenido en el response.
     */
    @Bean(name = "mapperService")
    public void mapperByService() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", configProperties.getContentType());
        for (String regionId : configProperties.getRegionId()){
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            for(String parameter :configProperties.getParameters()){
                params.add(parameter,regionId);
            }
            HttpEntity entity = new HttpEntity(headers);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(configProperties.getApi())
                    .queryParams(params);
            HttpEntity<String> response = restTemplate.exchange(builder.buildAndExpand(configProperties.getApi()).toString(), HttpMethod.GET, entity, String.class, params);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            for (Iterator<JsonNode> it = root.iterator(); it.hasNext(); ) {
                JsonNode jsonNode = it.next();
                JsonToDtoMaping jsonToDtoMaping = mapper.treeToValue(jsonNode, JsonToDtoMaping.class);
                jsonToDtoMapings.add(jsonToDtoMaping);
            }
        }

    }

    /**
     * Realiza una busqueda dentro del json ya Mapeado. buscando por cada nodo si se encuentra la IdRegion buscada. 
     * @return retorna una lista de farmacias asociadas a esa ID region
     */
    public List<FarmaciasDto> buscarFarmaciasPorIdRegion(String idRegion){
               return  jsonToDtoMapings
                .stream()
                .filter(nodo->nodo.getFkRegion().equalsIgnoreCase(idRegion))
                .map(a -> modelMapper.map(a, FarmaciasDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Se filtran las farmacias abiertas segun una comuna y hora buscando en los nodos del arbol Json mapeado
     */
    public List<FarmaciasDto> farmaciasDeTurnoRM(String comuna,String horaActual) throws IOException {
        return jsonToDtoMapings.stream()
                    .filter(nodo->nodo.getFkRegion().equalsIgnoreCase(String.valueOf(7))
                    && nodo.getComunaNombre().toUpperCase().equalsIgnoreCase(comuna.toUpperCase())
                    && dateUtils.isHourInInterval(horaActual,nodo.getFuncionamientoHoraApertura(),nodo.getFuncionamientoHoraCierre()))
                    .map(result->modelMapper.map(result,FarmaciasDto.class)).collect(Collectors.toList());


    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public List<JsonToDtoMaping> getMappersFarmacias() {
        return jsonToDtoMapings;
    }

    public void setMappersFarmacias(List<JsonToDtoMaping> jsonToDtoMapings) {
        this.jsonToDtoMapings = jsonToDtoMapings;
    }
}
