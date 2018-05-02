package org.sirix.io.bytepipe;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for the decorator, representing any byte representation to be serialized or to
 * serialize.
 * 
 * @author Sebastian Graf, University of Konstanz
 * 
 */
public interface ByteHandler {

  /**
   * Method to serialize any byte-chunk.
   * 
   * @param toSerialize byte to be serialized
   * @return result of the serialization
   * @throws IOException if a byte handling exception occurs
   */
  OutputStream serialize(OutputStream toSerialize) throws IOException;

  /**
   * Method to deserialize any byte-chunk.
   * 
   * @param toDeserialize to deserialize
   * @return result of the deserialization
   * @throws IOException if a byte handling exception occurs
   */
  InputStream deserialize(InputStream toDeserialize) throws IOException;

  /**
   * Method to retrieve a new instance.
   * 
   * @return new instance
   */
  ByteHandler getInstance();
}
