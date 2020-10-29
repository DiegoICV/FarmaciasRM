package farmacias.de.turno.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import farmacias.de.turno.config.FarmaciasDeTurnoRestApiConfig;
import farmacias.de.turno.config.DateUtils;
import farmacias.de.turno.dto.FarmaciasDto;
import farmacias.de.turno.mapper.FarmacyObjectMapper;

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
public class FarmaciasRepository implements IMapperByService {

    @Autowired
    FarmaciasDeTurnoRestApiConfig configProperties;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
     ModelMapper modelMapper;

    @Autowired
    DateUtils dateUtils;

    List<FarmacyObjectMapper> farmacyObjectMappers = new ArrayList<>();


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    /**
     * Por medio de un been cargamos todas las farmacias en una lista
     * @throws IOException
     */
    @Bean(name = "mapperServicePharmacy")
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
                FarmacyObjectMapper farmacyObjectMapper = mapper.treeToValue(jsonNode, FarmacyObjectMapper.class);
                farmacyObjectMappers.add(farmacyObjectMapper);
            }
        }

    }

    /**
     * Se buscan farmacias por region
     * @param idRegion
     * @return
     */
    public List<FarmaciasDto> findPharmacyByIdRegion(String idRegion){
               return  farmacyObjectMappers
                .stream()
                .filter(a->a.getFkRegion().equalsIgnoreCase(idRegion))
                .map(a -> modelMapper.map(a, FarmaciasDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Se buscan las farmacias tomando en cuenta la comuna , el nombre del local y la hora
     * @param comuna
     * @param localNombre
     * @param horaActual
     * @return
     * @throws IOException
     */
    public List<FarmaciasDto> farmaciasDeTurno(String comuna,String localNombre,String horaActual) throws IOException {
        // ComunaDto comunaDto = comunaServiceImpl.findComunaByName(comuna.toUpperCase());
        //Funcion que busca la Id region de la comuna por el nombre
        return farmacyObjectMappers.stream()
                    .filter(a->a.getFkRegion().equalsIgnoreCase(String.valueOf(7))
                    && a.getLocalNombre().toUpperCase().equalsIgnoreCase(localNombre.toUpperCase())
                    && a.getComunaNombre().toUpperCase().equalsIgnoreCase(comuna.toUpperCase())
                    && dateUtils.isHourInInterval(horaActual,a.getFuncionamientoHoraApertura(),a.getFuncionamientoHoraCierre()))
                    .map(a->modelMapper.map(a,FarmaciasDto.class)).collect(Collectors.toList());


    }


    /**
     * Se buscan los locales por medio de una comuna
     * @param comunaDto
     * @return
     * @throws IOException
     */
    public List<String> localByComuna(String comuna,String idRegion) throws IOException {

        return farmacyObjectMappers.stream()
                .filter(a->a.getFkRegion().equalsIgnoreCase(String.valueOf(idRegion))
                        && a.getComunaNombre().toUpperCase().equalsIgnoreCase(comuna)).filter(distinctByKey(a->a.getLocalNombre()))
                .map(a->a.getLocalNombre()).collect(Collectors.toList());
    }


    public List<String> locales() throws IOException {
        return farmacyObjectMappers.stream()
                .filter(distinctByKey(a->a.getLocalNombre()))
                .map(a->a.getLocalNombre()).collect(Collectors.toList());
    }




    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public List<FarmacyObjectMapper> getPharmacyMappers() {
        return farmacyObjectMappers;
    }

    public void setPharmacyMappers(List<FarmacyObjectMapper> farmacyObjectMappers) {
        this.farmacyObjectMappers = farmacyObjectMappers;
    }
}
