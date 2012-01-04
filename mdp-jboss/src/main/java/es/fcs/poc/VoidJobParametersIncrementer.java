package es.fcs.poc;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersIncrementer;

public class VoidJobParametersIncrementer implements JobParametersIncrementer{

	public JobParameters getNext(JobParameters parameters) {
		System.out.println("-------------KKOTA-------------");
		return null;
	}

}
