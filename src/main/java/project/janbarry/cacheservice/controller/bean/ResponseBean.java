package project.janbarry.cacheservice.controller.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean {

    private String title;
    private String detail;
    /**
     * A URI reference that identifies the problem type.
     */
    private String type;
    /**
     * A URI reference that identifies the specific occurrence of the problem.
     */
    private String instance;

}
