package bmstu.ui7.web;

import groovy.json.JsonOutput;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import iu7.service.Mamdani;
import iu7.service.Sudgeno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mamdani")
@Slf4j
public class LogicControllerMamdani {
    @Autowired
    private Mamdani mamdani;

    @ApiOperation(value = "Считывание лингвистических переменных", tags = "readV", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "ЛП считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/vars")
    void readV(@RequestBody Object data) {
        mamdani.readVars(data);
    }

    @ApiOperation(value = "Считывание правил", tags = "readR", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "Правила считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/rules")
    void readR(@RequestBody Object data) {
        mamdani.readRule(data);
    }

    @ApiOperation(value = "Считывание функций", tags = "readF", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "ЛП считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/funcs")
    void readF(@RequestBody Object data) {
        mamdani.readFunction(data);
    }

    @ApiOperation(value = "Обратный логиченский вывод", tags = "checkLogic", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @GetMapping("/checkLogic?h={h_value}&d={d_value}")
    double checkLogic(@PathVariable("h_value") double h, @PathVariable("d_value") double d) {
        return mamdani.calculate(h,d,"m");
    }

    @ApiOperation(value = "Обратный логиченский вывод", tags = "checkLogic", authorizations = @Authorization("basic"))
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
