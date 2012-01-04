package es.fcs.poc;

import java.util.Map;
import java.util.Set;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class VoidJobParametersValidator implements JobParametersValidator {

	public void validate(JobParameters parameters)
			throws JobParametersInvalidException {
		Map<String, JobParameter> map = parameters.getParameters();
		Set<String> s = map.keySet();
		for (String string : s) {
			System.out.print(string);
			System.out.print("=");
			System.out.println(map.get(string));
		}
	}
}
