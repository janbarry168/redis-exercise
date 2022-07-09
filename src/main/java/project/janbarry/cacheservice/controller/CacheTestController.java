package project.janbarry.cacheservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@Tag(name = "Cache Test")
public interface CacheTestController {

    @Operation(summary = "query cache by key")
    @ApiResponses(value = {@ApiResponse(responseCode = "404", description = "cache not found")})
    @GetMapping("/query/key/{key}")
    String getCache(@PathVariable String key);

    @Operation(summary = "query cache by key list")
    @PostMapping("/query/keys")
    Map<String, String> getCaches(@RequestBody List<String> keyList);

    @Operation(summary = "save single cache")
    @PostMapping("/save/{key}/{value}")
    void setCache(@PathVariable String key, @PathVariable String value);

    @Operation(summary = "save multiple cache")
    @PostMapping("/save/keys")
    void setCaches(@RequestBody Map<String, String> keyValue);

    @Operation(summary = "save and lock cache", description = "lock one minute")
    @PostMapping("/lock/{key}/{value}")
    void lockCache(@PathVariable String key, @PathVariable String value) throws Exception ;

}
