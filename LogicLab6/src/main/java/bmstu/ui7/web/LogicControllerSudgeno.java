package bmstu.ui7.web;

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
@RequestMapping("/sudgeno")
@Slf4j
public class LogicControllerSudgeno {
    @Autowired
    private Sudgeno sudgeno;

    @ApiOperation(value = "Считывание лингвистических переменных", tags = "readV", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "ЛП считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/vars")
    void readV(@RequestBody Object data) {
        sudgeno.readVars(data);
    }

    @ApiOperation(value = "Считывание правил", tags = "readR", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "Правила считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/rules")
    void readR(@RequestBody Object data) {
        sudgeno.readRule(data);

    }

    @ApiOperation(value = "Считывание функций", tags = "readF", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "ЛП считаны верно", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @PostMapping("/funcs")
    void readF(@RequestBody Object data) {
        sudgeno.readFunction(data);

    }

    @ApiOperation(value = "Обратный логиченский вывод", tags = "checkLogic", authorizations = @Authorization("basic"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Неверный формат данных", response = ResponseEntity.class),
    })
    @GetMapping("/checkLogic?h={h_value}&d={d_value}")
    double checkLogic(@PathVariable("h_value") double h, @PathVariable("d_value") double d) {
        return sudgeno.calculate(h,d,"m");
    }

    @GetMapping("/getVarGraphics")
    Object getVarGraphics() {
        return sudgeno.getGraphicsForVar();
    }

    @GetMapping("/getRuleGraphics")
    Object getRuleGraphics() {
        return sudgeno.getGraphicsForRule();
    }

}
