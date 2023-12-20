package rest;

import com.s17g2.task.dto.DeveloperResponse;
import com.s17g2.task.model.Developer;
import com.s17g2.task.model.DeveloperFactory;
import com.s17g2.task.model.Experience;
import com.s17g2.task.tax.Taxable;
import com.s17g2.task.validation.DeveloperValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private Map<Integer, Developer> developers;
    private Taxable taxable;

    @PostConstruct
    public void init() {
        developers = new HashMap<Integer, Developer>();
    }

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse save(@RequestBody Developer developer) {
        Developer createdDeveloper =  DeveloperFactory.createDeveloper(developer, taxable);
        if(createdDeveloper != null) {
            developers.put(createdDeveloper.getId(), createdDeveloper);
        }
        return new DeveloperResponse(createdDeveloper, "succeed", HttpStatus.OK.value());
    }

    @GetMapping
    public List<Developer> getAll() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable("id") Integer id) {
        if(DeveloperValidation.isDeveloperExist(this.developers, id)) {
            return new DeveloperResponse(this.developers.get(id), "success", HttpStatus.OK.value());
        }

        return new DeveloperResponse(null, "developer does not exist", HttpStatus.NOT_FOUND.value());
    }

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable("id") Integer id, @RequestBody Developer developer){
        if(!DeveloperValidation.isDeveloperExist(this.developers, id)){
            return new DeveloperResponse(null, "developer already not exist id=" + id, HttpStatus.NOT_FOUND.value());
        }
        developer.setId(id);
        Developer updatedDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
        this.developers.put(updatedDeveloper.getId(), updatedDeveloper);
        return new DeveloperResponse(updatedDeveloper, "succeed", HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}")
    public DeveloperResponse delete(@PathVariable("id") Integer id){
        Developer removedDeveloper = this.developers.get(id);
        this.developers.remove(id);
        return new DeveloperResponse(removedDeveloper, "succeed", HttpStatus.OK.value());
    }
}
