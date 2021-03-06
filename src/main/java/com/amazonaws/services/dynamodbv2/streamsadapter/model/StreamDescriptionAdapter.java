/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.dynamodbv2.streamsadapter.model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.kinesis.model.Shard;
import com.amazonaws.services.kinesis.model.StreamDescription;
import com.amazonaws.services.kinesis.model.StreamStatus;

/**
 * Container for all information describing a single DynamoDB Stream.
 */
public class StreamDescriptionAdapter extends StreamDescription {

    private com.amazonaws.services.dynamodbv2.model.StreamDescription internalDescription;

    private List<Shard> shards;

    /**
     * Constructs a new description using a DynamoDBStreams object.
     *
     * @param streamDescription Instance of DynamoDBStreams StreamDescription
     */
    public StreamDescriptionAdapter(com.amazonaws.services.dynamodbv2.model.StreamDescription streamDescription) {
        internalDescription = streamDescription;
        shards = new ArrayList<Shard>();
        for(com.amazonaws.services.dynamodbv2.model.Shard shard : streamDescription.getShards()) {
            shards.add(new ShardAdapter(shard));
        }
    }

    /**
     * @return The ID of the stream being described.
     */
    @Override
    public String getStreamName() {
        return internalDescription.getStreamId();
    }

    @Override
    public void setStreamName(String streamName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StreamDescription withStreamName(String streamName) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return The Amazon Resource Name (ARN) for the stream being described.
     */
    @Override
    public String getStreamARN() {
        return internalDescription.getStreamARN();
    }

    @Override
    public void setStreamARN(String streamARN) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StreamDescription withStreamARN(String streamARN) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return The current status of the stream being described.
     */
    @Override
    public String getStreamStatus() {
        String status = internalDescription.getStreamStatus();
        switch(com.amazonaws.services.dynamodbv2.model.StreamStatus.fromValue(status)) {
        case ENABLED :
            status = StreamStatus.ACTIVE.toString();
            break;
        case ENABLING :
            status = StreamStatus.CREATING.toString();
            break;
        // streams are valid for 24hrs after disabling and
        // will continue to support read operations
        case DISABLED :
            status = StreamStatus.ACTIVE.toString();
            break;
        case DISABLING :
            status = StreamStatus.ACTIVE.toString();
            break;
        }
        return status;
    }

    @Override
    public void setStreamStatus(String streamStatus) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StreamDescription withStreamStatus(String streamStatus) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setStreamStatus(StreamStatus streamStatus) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StreamDescription withStreamStatus(StreamStatus streamStatus) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return The shards that comprise the stream.
     */
    @Override
    public List<Shard> getShards() {
        return shards;
    }

    @Override
    public void setShards(java.util.Collection<Shard> shards) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StreamDescription withShards(Shard... shards) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StreamDescription withShards(java.util.Collection<Shard> shards) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return If true there are more shards in the stream
     *          available to describe.
     */
    @Override
    public Boolean isHasMoreShards() {
        return internalDescription.getLastEvaluatedShardId() != null;
    }

    /**
     * @return If true there are more shards in the stream
     *          available to describe.
     */
    @Override
    public Boolean getHasMoreShards() {
        return internalDescription.getLastEvaluatedShardId() != null;
    }

    @Override
    public void setHasMoreShards(Boolean hasMoreShards) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StreamDescription withHasMoreShards(Boolean hasMoreShards) {
        throw new UnsupportedOperationException();
    }

}
