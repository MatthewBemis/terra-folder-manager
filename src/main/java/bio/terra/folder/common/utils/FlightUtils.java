package bio.terra.folder.common.utils;

import bio.terra.folder.generated.model.ErrorReport;
import bio.terra.folder.service.job.JobMapKeys;
import bio.terra.stairway.FlightContext;
import bio.terra.stairway.FlightMap;
import org.springframework.http.HttpStatus;

/** Common methods for building flights */
public final class FlightUtils {
  private FlightUtils() {}

  /**
   * Build an error model and set it as the response
   *
   * @param context
   * @param message
   * @param responseStatus
   */
  public static void setErrorResponse(
      FlightContext context, String message, HttpStatus responseStatus) {
    ErrorReport errorModel = new ErrorReport().message(message);
    setResponse(context, errorModel, responseStatus);
  }

  /**
   * Set the response and status code in the result map.
   *
   * @param context flight context
   * @param responseObject response object to set
   * @param responseStatus status code to set
   */
  public static void setResponse(
      FlightContext context, Object responseObject, HttpStatus responseStatus) {
    FlightMap workingMap = context.getWorkingMap();
    workingMap.put(JobMapKeys.RESPONSE.getKeyName(), responseObject);
    workingMap.put(JobMapKeys.STATUS_CODE.getKeyName(), responseStatus);
  }
}
