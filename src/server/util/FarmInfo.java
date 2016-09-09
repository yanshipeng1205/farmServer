package server.util;

import com.hibernate.domain.Controllers;
import com.hibernate.domain.SensorStatus;

public class FarmInfo {
	private  final String TOKENFLAG=String.format("%c", 0x11);
	public int airTemperature;
	public int airHumidity;
	public int soilTemperature;
	public int soilHumidity;
	public int lightIntensity;
	public int pumpDuty;
	public int lightLevel;
	public int fanDuty;
	public int steerDuty;
	
	
	
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(airTemperature);
		strBuilder.append(TOKENFLAG);
		
		strBuilder.append(airHumidity);
		strBuilder.append(TOKENFLAG);
		
		strBuilder.append(soilTemperature);
		strBuilder.append(TOKENFLAG);
		
		strBuilder.append(soilHumidity);
		strBuilder.append(TOKENFLAG);
		
		strBuilder.append(lightIntensity);
		strBuilder.append(TOKENFLAG);
		
		strBuilder.append(pumpDuty);
		strBuilder.append(TOKENFLAG);
		
		strBuilder.append(lightLevel);
		strBuilder.append(TOKENFLAG);

		strBuilder.append(fanDuty);
		strBuilder.append(TOKENFLAG);

		strBuilder.append(steerDuty);

		return strBuilder.toString();
	}

	public FarmInfo()
	{
		
	}

	public FarmInfo(int xAirTemperature, int xAirHumidity, int xSoilTemperature,
					int xSoilHumidity, int xLightIntensity, int xPumpDuty,
					int xLightDuty, int xFanDuty, int xSteerDuty)
	{
		setAirTemperature(xAirTemperature);
		setAirHumidity(xAirHumidity);
		setSoilTemperature(xSoilTemperature);
		setSoilHumidity(xSoilHumidity);
		setLightIntensity(xLightIntensity);
		setPumpDuty(xPumpDuty);
		setLightIntensity(xLightIntensity);
		setFanDuty(xFanDuty);
		setSteerDuty(xSteerDuty);
	}

	public FarmInfo(SensorStatus sensorStatus, Controllers controllers)
	{
		setAirHumidity(sensorStatus.getAirHumidity());
		setAirTemperature(sensorStatus.getAirTemperature());
		setSoilTemperature(sensorStatus.getSoilTemperature());
		setSoilHumidity(sensorStatus.getSoilHumidity());
		setLightIntensity(sensorStatus.getLightIntensity());
		
		setPumpDuty(controllers.getPumpDuty());
		setLightLevel(controllers.getLightLevel());
		setFanDuty(controllers.getFanDuty());
		setSteerDuty(controllers.getSteerDuty());
	}
	
	
	public void setAirTemperature(int airTemperature) {
		this.airTemperature = airTemperature;
	}

	public void setAirHumidity(int airHumidity) {
		this.airHumidity = airHumidity;
	}

	public void setSoilTemperature(int soilTemperature) {
		this.soilTemperature = soilTemperature;
	}

	public void setSoilHumidity(int soilHumidity) {
		this.soilHumidity = soilHumidity;
	}

	public void setLightIntensity(int lightIntensity) {
		this.lightIntensity = lightIntensity;
	}

	public void setPumpDuty(int pumpDuty) {
		this.pumpDuty = pumpDuty;
	}

	public void setLightLevel(int lightLevel) {
		this.lightLevel = lightLevel;
	}

	public void setFanDuty(int fanDuty) {
		this.fanDuty = fanDuty;
	}

	public void setSteerDuty(int steerDuty) {
		this.steerDuty = steerDuty;
	}


	
}
