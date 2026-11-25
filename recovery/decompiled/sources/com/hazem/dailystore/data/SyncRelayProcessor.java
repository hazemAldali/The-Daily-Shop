package com.hazem.dailystore.data;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncRelayProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001!Bn\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012K\u0010\u0006\u001aG\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002J\u001e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bJ\u001e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000RS\u0010\u0006\u001aG\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000RP\u0010\u0013\u001aB\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\b0\b\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u000e0\u000e \u0015* \u0012\f\u0012\n \u0015*\u0004\u0018\u00010\b0\b\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u000e0\u000e\u0018\u00010\u00140\u0014X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0016\u00a8\u0006\""}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayProcessor;", "", "repository", "Lcom/hazem/dailystore/data/SyncProtocolRepository;", "domainApplier", "Lcom/hazem/dailystore/data/SyncDomainApplier;", "sendFrame", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "operationId", "recipientDeviceId", "frameJson", "", "listener", "Lcom/hazem/dailystore/data/SyncRelayProcessor$Listener;", "<init>", "(Lcom/hazem/dailystore/data/SyncProtocolRepository;Lcom/hazem/dailystore/data/SyncDomainApplier;Lkotlin/jvm/functions/Function3;Lcom/hazem/dailystore/data/SyncRelayProcessor$Listener;)V", "pendingApplicationAckIds", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "onRelayReady", "", "sendApplicationAck", "onEncryptedFrame", "fromDeviceId", "frameId", "encryptedFrameJson", "onRelayDelivery", "forwarded", "retryable", "Listener", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SyncRelayProcessor {
    private final SyncDomainApplier domainApplier;
    private final Listener listener;
    private final ConcurrentHashMap.KeySetView<String, Boolean> pendingApplicationAckIds;
    private final SyncProtocolRepository repository;
    private final Function3<String, String, String, Boolean> sendFrame;

    /* JADX INFO: compiled from: SyncRelayProcessor.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u001a\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0016\u00a8\u0006\f"}, d2 = {"Lcom/hazem/dailystore/data/SyncRelayProcessor$Listener;", "", "onOperationApplied", "", "operationId", "", "onApplicationAckAccepted", "duplicate", "", "onRetryRequired", "reason", "onProtocolError", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Listener {

        /* JADX INFO: compiled from: SyncRelayProcessor.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static void onApplicationAckAccepted(Listener listener, String operationId, boolean z) {
                Intrinsics.checkNotNullParameter(operationId, "operationId");
            }

            public static void onOperationApplied(Listener listener, String operationId) {
                Intrinsics.checkNotNullParameter(operationId, "operationId");
            }

            public static void onProtocolError(Listener listener, String str, String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
            }

            public static void onRetryRequired(Listener listener, String operationId, String reason) {
                Intrinsics.checkNotNullParameter(operationId, "operationId");
                Intrinsics.checkNotNullParameter(reason, "reason");
            }
        }

        void onApplicationAckAccepted(String operationId, boolean duplicate);

        void onOperationApplied(String operationId);

        void onProtocolError(String operationId, String reason);

        void onRetryRequired(String operationId, String reason);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SyncRelayProcessor(SyncProtocolRepository repository, SyncDomainApplier domainApplier, Function3<? super String, ? super String, ? super String, Boolean> sendFrame, Listener listener) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(domainApplier, "domainApplier");
        Intrinsics.checkNotNullParameter(sendFrame, "sendFrame");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.repository = repository;
        this.domainApplier = domainApplier;
        this.sendFrame = sendFrame;
        this.listener = listener;
        this.pendingApplicationAckIds = ConcurrentHashMap.newKeySet();
    }

    public /* synthetic */ SyncRelayProcessor(SyncProtocolRepository syncProtocolRepository, SyncDomainApplier syncDomainApplier, Function3 function3, Listener listener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(syncProtocolRepository, syncDomainApplier, function3, (i & 8) != 0 ? new Listener() { // from class: com.hazem.dailystore.data.SyncRelayProcessor.1
            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onApplicationAckAccepted(String str, boolean z) {
                Listener.DefaultImpls.onApplicationAckAccepted(this, str, z);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onOperationApplied(String str) {
                Listener.DefaultImpls.onOperationApplied(this, str);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onProtocolError(String str, String str2) {
                Listener.DefaultImpls.onProtocolError(this, str, str2);
            }

            @Override // com.hazem.dailystore.data.SyncRelayProcessor.Listener
            public void onRetryRequired(String str, String str2) {
                Listener.DefaultImpls.onRetryRequired(this, str, str2);
            }
        } : listener);
    }

    public final void onRelayReady() throws IOException {
        List<String> listListOf;
        String strLocalDeviceRole = this.repository.localDeviceRole();
        if (strLocalDeviceRole == null) {
            throw new IllegalStateException("\u062f\u0648\u0631 \u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623");
        }
        if (Intrinsics.areEqual(strLocalDeviceRole, "PRIMARY")) {
            listListOf = this.repository.activeSecondaryDeviceIds();
        } else {
            if (!Intrinsics.areEqual(strLocalDeviceRole, "SECONDARY")) {
                throw new IllegalStateException("\u062f\u0648\u0631 \u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641");
            }
            String strAuthorityDeviceId = this.repository.authorityDeviceId();
            if (strAuthorityDeviceId == null) {
                throw new IllegalStateException("\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u063a\u064a\u0631 \u0645\u062d\u062f\u062f");
            }
            listListOf = CollectionsKt.listOf(strAuthorityDeviceId);
        }
        int i = 50;
        for (String str : listListOf) {
            if (i <= 0) {
                return;
            }
            for (String str2 : SyncProtocolRepository.appliedOperationsAwaitingAckForDevice$default(this.repository, str, i, 0L, 4, null)) {
                if (sendApplicationAck(str2, str)) {
                    i--;
                    if (i <= 0) {
                        break;
                    }
                } else {
                    this.listener.onRetryRequired(str2, "\u062a\u0639\u0630\u0631 \u0625\u0631\u0633\u0627\u0644 \u0625\u0642\u0631\u0627\u0631 \u0627\u0644\u062a\u0637\u0628\u064a\u0642\u061b \u0628\u0642\u064a \u0645\u062d\u0641\u0648\u0638\u0627\u064b \u0644\u0625\u0639\u0627\u062f\u0629 \u0627\u0644\u0645\u062d\u0627\u0648\u0644\u0629");
                    return;
                }
            }
            if (i > 0) {
                for (SyncOutboxItem syncOutboxItem : this.repository.pendingOutboxForDevice(str, i)) {
                    if (this.sendFrame.invoke(syncOutboxItem.getOperationId(), str, this.repository.encryptedAuthenticatedOutbox(syncOutboxItem.getOperationId(), str)).booleanValue()) {
                        i--;
                        if (i <= 0) {
                            break;
                        }
                    } else {
                        this.listener.onRetryRequired(syncOutboxItem.getOperationId(), "\u062a\u0639\u0630\u0631 \u0625\u0631\u0633\u0627\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629\u061b \u0628\u0642\u064a\u062a \u0645\u062d\u0641\u0648\u0638\u0629 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0625\u0631\u0633\u0627\u0644");
                        return;
                    }
                }
            }
        }
    }

    private final boolean sendApplicationAck(String operationId, String recipientDeviceId) throws JSONException, IOException {
        String strCreateEncryptedApplicationAck = this.repository.createEncryptedApplicationAck(recipientDeviceId, operationId);
        this.pendingApplicationAckIds.add(operationId);
        boolean zBooleanValue = this.sendFrame.invoke(operationId, recipientDeviceId, strCreateEncryptedApplicationAck).booleanValue();
        if (!zBooleanValue) {
            this.pendingApplicationAckIds.remove(operationId);
        }
        return zBooleanValue;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0129, code lost:
    
        if (r7.equals("APPLIED") != false) goto L65;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onEncryptedFrame(String fromDeviceId, String frameId, String encryptedFrameJson) throws Throwable {
        AuthenticatedSyncEnvelope authenticatedSyncEnvelopeVerifyEncryptedIncomingFromPrimary;
        SyncInboxDisposition syncInboxDispositionRecordIncomingFromPrimary;
        Intrinsics.checkNotNullParameter(fromDeviceId, "fromDeviceId");
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(encryptedFrameJson, "encryptedFrameJson");
        try {
            if (StringsKt.isBlank(frameId) || frameId.length() > 80) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            JSONObject jSONObject = new JSONObject(encryptedFrameJson);
            if (!Intrinsics.areEqual(jSONObject.optString("operation_id"), frameId) || !Intrinsics.areEqual(jSONObject.optString("sender_device_id"), fromDeviceId) || !Intrinsics.areEqual(jSONObject.optString("recipient_device_id"), this.repository.localDeviceId())) {
                throw new IllegalArgumentException("\u0625\u0637\u0627\u0631 \u0627\u0644\u0648\u0633\u064a\u0637 \u0644\u0627 \u064a\u0637\u0627\u0628\u0642 \u0647\u0648\u064a\u0629 \u0627\u0644\u0637\u0631\u0641\u064a\u0646 \u0623\u0648 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629".toString());
            }
            if (Intrinsics.areEqual(jSONObject.optString("frame_type"), "APPLICATION_ACK")) {
                this.listener.onApplicationAckAccepted(frameId, this.repository.acceptEncryptedApplicationAck(encryptedFrameJson) == SyncApplicationAckDisposition.DUPLICATE);
                return;
            }
            String strLocalDeviceRole = this.repository.localDeviceRole();
            if (Intrinsics.areEqual(strLocalDeviceRole, "PRIMARY")) {
                authenticatedSyncEnvelopeVerifyEncryptedIncomingFromPrimary = this.repository.verifyEncryptedIncomingFromSecondary(encryptedFrameJson);
            } else {
                if (!Intrinsics.areEqual(strLocalDeviceRole, "SECONDARY")) {
                    throw new IllegalStateException("\u062f\u0648\u0631 \u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0645\u0647\u064a\u0623");
                }
                authenticatedSyncEnvelopeVerifyEncryptedIncomingFromPrimary = this.repository.verifyEncryptedIncomingFromPrimary(encryptedFrameJson);
            }
            if (!Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyEncryptedIncomingFromPrimary.getOperationId(), frameId) || !Intrinsics.areEqual(authenticatedSyncEnvelopeVerifyEncryptedIncomingFromPrimary.getOriginDeviceId(), fromDeviceId)) {
                throw new IllegalArgumentException("\u0647\u0648\u064a\u0629 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0645\u0641\u0643\u0648\u0643\u0629 \u0644\u0627 \u062a\u0637\u0627\u0628\u0642 \u0625\u0637\u0627\u0631 \u0627\u0644\u0646\u0642\u0644".toString());
            }
            String strEncode = SyncEnvelopeSecurity.INSTANCE.encode(authenticatedSyncEnvelopeVerifyEncryptedIncomingFromPrimary);
            if (Intrinsics.areEqual(strLocalDeviceRole, "PRIMARY")) {
                syncInboxDispositionRecordIncomingFromPrimary = this.repository.recordIncomingFromSecondary(strEncode);
            } else {
                if (!Intrinsics.areEqual(strLocalDeviceRole, "SECONDARY")) {
                    throw new IllegalStateException("\u062f\u0648\u0631 \u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
                }
                syncInboxDispositionRecordIncomingFromPrimary = this.repository.recordIncomingFromPrimary(strEncode);
            }
            if (syncInboxDispositionRecordIncomingFromPrimary == SyncInboxDisposition.CONFLICT) {
                throw new IllegalArgumentException("\u062a\u0639\u0627\u0631\u0636\u062a \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629 \u0645\u0639 \u0639\u0645\u0644\u064a\u0629 \u0645\u062d\u0641\u0648\u0638\u0629 \u0628\u0627\u0644\u0645\u0639\u0631\u0651\u0641 \u0646\u0641\u0633\u0647".toString());
            }
            String strIncomingOperationStatus = this.repository.incomingOperationStatus(frameId);
            if (strIncomingOperationStatus != null) {
                int iHashCode = strIncomingOperationStatus.hashCode();
                if (iHashCode != -75252643) {
                    if (iHashCode != -26093087) {
                        if (iHashCode == 174130302 && strIncomingOperationStatus.equals("REJECTED")) {
                            throw new IllegalArgumentException("\u0633\u0628\u0642 \u0631\u0641\u0636 \u0627\u0644\u0639\u0645\u0644\u064a\u0629\u061b \u0644\u0646 \u064a\u0635\u062f\u0631 \u0625\u0642\u0631\u0627\u0631 \u062a\u0637\u0628\u064a\u0642");
                        }
                    } else if (strIncomingOperationStatus.equals("RECEIVED")) {
                        SyncDomainApplier.ApplyResult applyResultApplyReceivedOperation = this.domainApplier.applyReceivedOperation(frameId);
                        if (!applyResultApplyReceivedOperation.getApplied()) {
                            String reason = applyResultApplyReceivedOperation.getReason();
                            if (reason == null) {
                                reason = "\u0644\u0645 \u064a\u0643\u062a\u0645\u0644 \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0639\u0644\u0649 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a";
                            }
                            throw new IllegalArgumentException(reason.toString());
                        }
                        this.listener.onOperationApplied(frameId);
                        if (sendApplicationAck(frameId, fromDeviceId)) {
                            return;
                        }
                        this.listener.onRetryRequired(frameId, "\u062a\u0645 \u062a\u0637\u0628\u064a\u0642 \u0627\u0644\u0639\u0645\u0644\u064a\u0629\u060c \u0644\u0643\u0646 \u062a\u0639\u0630\u0631 \u0625\u0631\u0633\u0627\u0644 \u0627\u0644\u0625\u0642\u0631\u0627\u0631\u061b \u0633\u064a\u0639\u0627\u062f \u0625\u0631\u0633\u0627\u0644\u0647 \u0639\u0646\u062f \u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u062a\u0627\u0644\u064a");
                        return;
                    }
                }
            }
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062f \u062d\u0627\u0644\u0629 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0648\u0627\u0631\u062f\u0629");
        } catch (Exception e) {
            Listener listener = this.listener;
            String message = e.getMessage();
            if (message == null) {
                message = "\u0641\u0634\u0644 \u0645\u0639\u0627\u0644\u062c\u0629 \u0625\u0637\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629";
            }
            listener.onProtocolError(frameId, message);
        }
    }

    public final void onRelayDelivery(String frameId, boolean forwarded, boolean retryable) {
        String str;
        Object objM381constructorimpl;
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        if (forwarded && !retryable && this.pendingApplicationAckIds.remove(frameId)) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncRelayProcessor syncRelayProcessor = this;
                objM381constructorimpl = Result.m381constructorimpl(Boolean.valueOf(this.repository.markApplicationAckForwarded(frameId)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(objM381constructorimpl);
            if (thM384exceptionOrNullimpl != null) {
                Listener listener = this.listener;
                String message = thM384exceptionOrNullimpl.getMessage();
                if (message == null) {
                    message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0645\u062d\u0627\u0648\u0644\u0629 \u062a\u0645\u0631\u064a\u0631 \u0627\u0644\u0625\u0642\u0631\u0627\u0631";
                }
                listener.onProtocolError(frameId, message);
            }
        }
        if (!forwarded || retryable) {
            Listener listener2 = this.listener;
            if (retryable) {
                str = "\u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0645\u0642\u0627\u0628\u0644 \u063a\u064a\u0631 \u0645\u062a\u0635\u0644\u061b \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0628\u0627\u0642\u064a\u0629 \u0641\u064a \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0625\u0631\u0633\u0627\u0644";
            } else {
                str = "\u0644\u0645 \u064a\u0624\u0643\u062f \u0627\u0644\u0648\u0633\u064a\u0637 \u062a\u0645\u0631\u064a\u0631 \u0627\u0644\u0631\u0633\u0627\u0644\u0629";
            }
            listener2.onRetryRequired(frameId, str);
        }
    }
}
