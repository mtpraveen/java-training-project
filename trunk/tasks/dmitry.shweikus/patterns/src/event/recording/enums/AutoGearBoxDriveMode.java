/**
 * 
 */
package event.recording.enums;

/**
 * @author dima
 *
 */
public enum AutoGearBoxDriveMode
{
	PARK,REVERSE,NEUTRAL,DRIVE,LOW;
	public static class DriveModeObject
	{
		private AutoGearBoxDriveMode mode;

		/**
		 * @param mode
		 */
		public DriveModeObject(AutoGearBoxDriveMode mode) {
			super();
			this.mode = mode;
		}

		/**
		 * @return the mode
		 */
		public AutoGearBoxDriveMode getMode()
		{
			return mode;
		}
	}
}
