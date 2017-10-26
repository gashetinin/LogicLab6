package Lab6.web;

import groovy.util.logging.Slf4j;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import Lab6.service.Mamdani;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mamdani")
@Slf4j
public class LogicControllerMamdani {
    @Autowired
    private Mamdani mamdani;

    @ApiOperation(value = "Считывание лингвистических переменных", tags = "readV")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ЛП считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/vars")
    void readV(@RequestBody Object data) {
        mamdani.readVars(data);
    }

    @ApiOperation(value = "Считывание правил", tags = "readR")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Правила считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/rules")
    void readR(@RequestBody Object data) {
        mamdani.readRule(data);
    }

    @ApiOperation(value = "Считывание функций", tags = "readF")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ЛП считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/funcs")
    void readF(@RequestBody Object data) {
        mamdani.readFunction(data);
    }

    @ApiOperation(value = "Обратный логиченский вывод", tags = "checkLogic")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @GetMapping("/checkLogic/{h_value}/{d_value}")
    Double checkLogic(@PathVariable("h_value") double h, @PathVariable("d_value") double d) {
        return mamdani.calculate(h,d,"m");
    }

    @ApiOperation(value = "Обратный логиченский вывод", tags = "checkLogic")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @GetMapping("/getVarGraphics")
    Object getVarGraphics() {
        return mamdani.getGraphicsForVar();
    }

    @GetMapping("/getRuleGraphics")
    Object getRuleGraphics() {
        return mamdani.getGraphicsForRule();
    }

    @GetMapping("/getResultGraphics")
    Object getResultGraphics() {
        return mamdani.getGraphicsTotal();
    }

}
