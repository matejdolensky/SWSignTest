package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ClientDto {
    private String uuid;
    private String name;
    private String description;
    List<ProductDto> products;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ProductDto {
    private String uuid;
    private String productName;
    private String productDescription;
    private String keyGenerationType;
    private String keyType;
    private boolean issuanceNotification;
    private boolean allowAdditionalIssuanceNotificationRecipients;
    private boolean revocationNotification;
    private boolean allowAdditionalRevocationNotificationRecipients;
    private boolean authorization;
    private boolean allowAdditionalAuthorizationNotificationRecipients;
    private boolean allowAdditionalAuthorizationAcceptedNotificationRecipients;
    private boolean allowAdditionalAuthorizationRejectedNotificationRecipients;
    private boolean renewalRule;
    private boolean allowAdditionalRenewalNotificationRecipients;
    private boolean publishCertificate;
    private boolean clientPublishCertificateOverride;
    private boolean clientPublishCertificateOverrideDefault;
    private boolean requiresRegistrationDocuments;
    private boolean requiresRegistrationDocumentsOnRegister;
    private boolean allowRegistrationDocumentsPDF;
    private boolean allowRegistrationDocumentsJPG;
    private boolean isCABDNSValidationRequired;
    private boolean allowAdditionalCABDNSNotificationRecipients;
    private boolean isCABDNSEmailLinkValidationRequired;
    private boolean isEmailBoxValidationRequired;
    private boolean isGenerateRevocationCode;
    private String expirationDate; // null in your sample, could be LocalDateTime if needed
    private ProductValidityDto productValidity;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ProductValidityDto {
    private String validityType;
    private Integer validity;
    private List<String> validityOptions;
}
