### Documentación 
Se utilizo Spring boot, maven, Jdk 14, ademas de swagger para documentar los servicios expuestos,
se preparo la datos al iniciar el componente para no tener que llamarlo en cada petición al
filtro, ademas se utilizo ```properties``` para señalar los servicios.

##Veamos

Se configura el properties con los campos necesarios.
```java

request.pharmacy.api=https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion
request.pharmacy.parameters=id_region
request.pharmacy.contentType=application/json
request.pharmacy.method=get
request.pharmacy.regionId=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15

```

##Preparacion de servicios que nos provee los datos : 
Por medio del prefix, somos capaces de prepara el componente
[ver referencia prefix](https://www.baeldung.com/configuration-properties-in-spring-boot)
```java
/**
 * Esta clase se encarga de preparar el consumo del servicio de las farmacias
 */
@ConfigurationProperties(prefix = "request.pharmacy")
@Component
public class ApiRestPharmacyConfig extends BaseRequestApi {
...
}


/**
 * Esta clase se encarga de configurar el consumo de la api rest del servicio de comuna
 */
@ConfigurationProperties(prefix = "request.comuna")
@Component
public class ApiRestComunaConfig extends BaseRequestApi{... }

```

###Carga de datos

Para esto se consumen los servicicos usando [restTemplate](https://www.baeldung.com/rest-template)
se crearon ```repositorios``` donde configuramos toda la data y despues se hacen los filtros correspondientes

* **Para las comunas** :
```java 
/**
     * Se cargan todas comunas por medio de un componente en una lista
     * @throws IOException
     */
  @Bean(name = "mapperServiceComuna")
     public void mapperByService() throws IOException {
         String response;
         HttpHeaders headers =  new HttpHeaders();
         headers.setContentType(MediaType.MULTIPART_FORM_DATA);
         LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
         for(int i: configProperties.getRegionId()){
             for(String parameter :configProperties.getParameters()) {
                 map.add(parameter, i);
             }
             HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
             response = restTemplate.postForObject(configProperties.getApi(), requestEntity, String.class);
             Reader reader = new StringReader(response);
             HTMLEditorKit.Parser parser = new ParserDelegator();
             HTMLTableParser htmlTableParser = new HTMLTableParser(i);
             parser.parse(reader, htmlTableParser, true);
             comunas.put(i,htmlTableParser.getComunaDtoList());
             comunaDtos.addAll(htmlTableParser.getComunaDtoList());
         }
 
     }
```

* **Par las farmacias:**
```java 

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
                PharmacyMapper pharmacyMapper = mapper.treeToValue(jsonNode, PharmacyMapper.class);
                pharmacyMappers.add(pharmacyMapper);
            }
        }

    }

```


### Configuracion de servicios rest
El servicio principal, esta  en ```PharmacyController``` : 

```java
/**
     * Retorna todas las farmacias de turno en una comuna
     * @param comuna comuna donde buscaremos los locales
     * @param localNombre local a buscar
     * @param request
     * @return
     * @throws IOException
     */
    @GetMapping("/pharmaciesOnDuty")
    public List<PharmacyDto> getPharmaciesOnDuty(@RequestParam String comuna, @RequestParam String localNombre, HttpServletRequest request) throws IOException {
        return pharmacyService.getPharmaciesOnDuty(comuna,localNombre,Calendar.getInstance(request.getLocale()));
    }
``` 
Si embargo hay otros servicios expuestos pensando en el desarrollo del front end como los son : 

* El de comunas
```java
/**
     *
     * @return todas las comunas
     * @throws IOException
     */
    @GetMapping("/comunas")
    public List<ComunaDto> getComunas() throws IOException {
        return comunaService.allComunas();
    }
```
* El de locales por comuna:

```java
 /**
      * Retorna todos los locales dentre de una comuna
      * @param comunaDto
      * @return
      * @throws IOException
      */
     @PostMapping("/localByComuna")
     public List<String> getLocalesPorComuna(@RequestBody ComunaDto comunaDto) throws IOException {
         return pharmacyService.getLocalByComuna(comunaDto);
     }
```

##Deploy
[Se hizo despliegue en gcp , se utilizo cloud run, aca puedes ver las servicios expuestos](https://pharmacy-service-ftsn5nwobq-uc.a.run.app/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)



