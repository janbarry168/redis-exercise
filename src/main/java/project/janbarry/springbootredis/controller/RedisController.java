package project.janbarry.springbootredis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Cache Test")
public interface RedisController {

    @Operation(summary = "query cache by key")
    @ApiResponses(value = {@ApiResponse(responseCode = "404", description = "cache not found")})
    @GetMapping("key/{key}")
    String getCache(@PathVariable String key);

    @Operation(summary = "query cache by key list")
    @GetMapping("keys")
    Map<String, String> getCaches(@RequestParam("key") List<String> keyList);

    @Operation(summary = "save single cache")
    @PostMapping("/{key}/{value}")
    void setCache(@PathVariable String key, @PathVariable String value);

    @Operation(summary = "save multiple cache")
    @PostMapping("/keys")
    void setCaches(@RequestBody Map<String, String> keyValue);

    @Operation(summary = "save and lock cache", description = "lock one minute")
    @PostMapping("/lock/{key}/{value}")
    void lockCache(@PathVariable String key, @PathVariable String value) throws Exception;

}
