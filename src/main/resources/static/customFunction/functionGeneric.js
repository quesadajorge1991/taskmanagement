/**
 * 
 */


function loadCheckBoxGroup(valuesCheckBox, autoritiesModel) {
	
	var autoritiesByGroup = valuesCheckBox;/* todos los permisos establecidos en la app */
	var permisos = autoritiesModel; /* todos los permisos o los grupos establecidos en la app */
	
	for (let i = 0; i < permisos.length; i++) {
		console.log('Iteration permisos ' + permisos[i]);
		var checkbox = document.getElementById(permisos[i]);
		for (let j = 0; j < autoritiesByGroup.length; j++) {
			if (autoritiesByGroup[j] == permisos[i]) {
				checkbox.checked = true;
			}
		}
	}

}
