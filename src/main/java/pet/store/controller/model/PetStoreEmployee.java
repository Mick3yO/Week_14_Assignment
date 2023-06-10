package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreEmployee {
private Long employeeId;
	private String EmployeeFirstName;
	private String EmployeeLastName;
	private String EmployeePhone;
	private String EmployeeJobTitle;
	PetStore petStore;
	
}
