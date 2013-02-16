/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.evernote.edam.type;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import com.evernote.thrift.*;
import com.evernote.thrift.protocol.*;

/**
 * A named search associated with the account that can be quickly re-used.
 * <dl>
 * <dt>guid</dt>
 *   <dd>The unique identifier of this search.  Will be set by the
 *   service, so may be omitted by the client when creating.
 *   <br/>
 *   Length:  EDAM_GUID_LEN_MIN - EDAM_GUID_LEN_MAX
 *   <br/>
 *   Regex:  EDAM_GUID_REGEX
 *   </dd>
 * 
 * <dt>name</dt>
 *   <dd>The name of the saved search to display in the GUI.  The
 *   account may only contain one search with a given name (case-insensitive
 *   compare). Can't begin or end with a space.
 *   <br/>
 *   Length:  EDAM_SAVED_SEARCH_NAME_LEN_MIN - EDAM_SAVED_SEARCH_NAME_LEN_MAX
 *   <br/>
 *   Regex:  EDAM_SAVED_SEARCH_NAME_REGEX
 *   </dd>
 * 
 * <dt>query</dt>
 *   <dd>A string expressing the search to be performed.
 *   <br/>
 *   Length:  EDAM_SAVED_SEARCH_QUERY_LEN_MIN - EDAM_SAVED_SEARCH_QUERY_LEN_MAX
 *   </dd>
 * 
 * <dt>format</dt>
 *   <dd>The format of the query string, to determine how to parse
 *   and process it.
 *   </dd>
 * 
 * <dt>updateSequenceNum</dt>
 *   <dd>A number identifying the last transaction to
 *   modify the state of this object.  The USN values are sequential within an
 *   account, and can be used to compare the order of modifications within the
 *   service.
 *   </dd>
 * </dl>
 */
public class SavedSearch implements TBase<SavedSearch>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("SavedSearch");

  private static final TField GUID_FIELD_DESC = new TField("guid", TType.STRING, (short)1);
  private static final TField NAME_FIELD_DESC = new TField("name", TType.STRING, (short)2);
  private static final TField QUERY_FIELD_DESC = new TField("query", TType.STRING, (short)3);
  private static final TField FORMAT_FIELD_DESC = new TField("format", TType.I32, (short)4);
  private static final TField UPDATE_SEQUENCE_NUM_FIELD_DESC = new TField("updateSequenceNum", TType.I32, (short)5);

  private String guid;
  private String name;
  private String query;
  private QueryFormat format;
  private int updateSequenceNum;


  // isset id assignments
  private static final int __UPDATESEQUENCENUM_ISSET_ID = 0;
  private boolean[] __isset_vector = new boolean[1];

  public SavedSearch() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SavedSearch(SavedSearch other) {
    System.arraycopy(other.__isset_vector, 0, __isset_vector, 0, other.__isset_vector.length);
    if (other.isSetGuid()) {
      this.guid = other.guid;
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetQuery()) {
      this.query = other.query;
    }
    if (other.isSetFormat()) {
      this.format = other.format;
    }
    this.updateSequenceNum = other.updateSequenceNum;
  }

  public SavedSearch deepCopy() {
    return new SavedSearch(this);
  }

  public void clear() {
    this.guid = null;
    this.name = null;
    this.query = null;
    this.format = null;
    setUpdateSequenceNumIsSet(false);
    this.updateSequenceNum = 0;
  }

  public String getGuid() {
    return this.guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public void unsetGuid() {
    this.guid = null;
  }

  /** Returns true if field guid is set (has been asigned a value) and false otherwise */
  public boolean isSetGuid() {
    return this.guid != null;
  }

  public void setGuidIsSet(boolean value) {
    if (!value) {
      this.guid = null;
    }
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been asigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getQuery() {
    return this.query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public void unsetQuery() {
    this.query = null;
  }

  /** Returns true if field query is set (has been asigned a value) and false otherwise */
  public boolean isSetQuery() {
    return this.query != null;
  }

  public void setQueryIsSet(boolean value) {
    if (!value) {
      this.query = null;
    }
  }

  /**
   * 
   * @see QueryFormat
   */
  public QueryFormat getFormat() {
    return this.format;
  }

  /**
   * 
   * @see QueryFormat
   */
  public void setFormat(QueryFormat format) {
    this.format = format;
  }

  public void unsetFormat() {
    this.format = null;
  }

  /** Returns true if field format is set (has been asigned a value) and false otherwise */
  public boolean isSetFormat() {
    return this.format != null;
  }

  public void setFormatIsSet(boolean value) {
    if (!value) {
      this.format = null;
    }
  }

  public int getUpdateSequenceNum() {
    return this.updateSequenceNum;
  }

  public void setUpdateSequenceNum(int updateSequenceNum) {
    this.updateSequenceNum = updateSequenceNum;
    setUpdateSequenceNumIsSet(true);
  }

  public void unsetUpdateSequenceNum() {
    __isset_vector[__UPDATESEQUENCENUM_ISSET_ID] = false;
  }

  /** Returns true if field updateSequenceNum is set (has been asigned a value) and false otherwise */
  public boolean isSetUpdateSequenceNum() {
    return __isset_vector[__UPDATESEQUENCENUM_ISSET_ID];
  }

  public void setUpdateSequenceNumIsSet(boolean value) {
    __isset_vector[__UPDATESEQUENCENUM_ISSET_ID] = value;
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SavedSearch)
      return this.equals((SavedSearch)that);
    return false;
  }

  public boolean equals(SavedSearch that) {
    if (that == null)
      return false;

    boolean this_present_guid = true && this.isSetGuid();
    boolean that_present_guid = true && that.isSetGuid();
    if (this_present_guid || that_present_guid) {
      if (!(this_present_guid && that_present_guid))
        return false;
      if (!this.guid.equals(that.guid))
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_query = true && this.isSetQuery();
    boolean that_present_query = true && that.isSetQuery();
    if (this_present_query || that_present_query) {
      if (!(this_present_query && that_present_query))
        return false;
      if (!this.query.equals(that.query))
        return false;
    }

    boolean this_present_format = true && this.isSetFormat();
    boolean that_present_format = true && that.isSetFormat();
    if (this_present_format || that_present_format) {
      if (!(this_present_format && that_present_format))
        return false;
      if (!this.format.equals(that.format))
        return false;
    }

    boolean this_present_updateSequenceNum = true && this.isSetUpdateSequenceNum();
    boolean that_present_updateSequenceNum = true && that.isSetUpdateSequenceNum();
    if (this_present_updateSequenceNum || that_present_updateSequenceNum) {
      if (!(this_present_updateSequenceNum && that_present_updateSequenceNum))
        return false;
      if (this.updateSequenceNum != that.updateSequenceNum)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(SavedSearch other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    SavedSearch typedOther = (SavedSearch)other;

    lastComparison = Boolean.valueOf(isSetGuid()).compareTo(typedOther.isSetGuid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGuid()) {      lastComparison = TBaseHelper.compareTo(this.guid, typedOther.guid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {      lastComparison = TBaseHelper.compareTo(this.name, typedOther.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetQuery()).compareTo(typedOther.isSetQuery());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuery()) {      lastComparison = TBaseHelper.compareTo(this.query, typedOther.query);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFormat()).compareTo(typedOther.isSetFormat());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFormat()) {      lastComparison = TBaseHelper.compareTo(this.format, typedOther.format);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUpdateSequenceNum()).compareTo(typedOther.isSetUpdateSequenceNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdateSequenceNum()) {      lastComparison = TBaseHelper.compareTo(this.updateSequenceNum, typedOther.updateSequenceNum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // GUID
          if (field.type == TType.STRING) {
            this.guid = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // NAME
          if (field.type == TType.STRING) {
            this.name = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // QUERY
          if (field.type == TType.STRING) {
            this.query = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // FORMAT
          if (field.type == TType.I32) {
            this.format = QueryFormat.findByValue(iprot.readI32());
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // UPDATE_SEQUENCE_NUM
          if (field.type == TType.I32) {
            this.updateSequenceNum = iprot.readI32();
            setUpdateSequenceNumIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.guid != null) {
      if (isSetGuid()) {
        oprot.writeFieldBegin(GUID_FIELD_DESC);
        oprot.writeString(this.guid);
        oprot.writeFieldEnd();
      }
    }
    if (this.name != null) {
      if (isSetName()) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(this.name);
        oprot.writeFieldEnd();
      }
    }
    if (this.query != null) {
      if (isSetQuery()) {
        oprot.writeFieldBegin(QUERY_FIELD_DESC);
        oprot.writeString(this.query);
        oprot.writeFieldEnd();
      }
    }
    if (this.format != null) {
      if (isSetFormat()) {
        oprot.writeFieldBegin(FORMAT_FIELD_DESC);
        oprot.writeI32(this.format.getValue());
        oprot.writeFieldEnd();
      }
    }
    if (isSetUpdateSequenceNum()) {
      oprot.writeFieldBegin(UPDATE_SEQUENCE_NUM_FIELD_DESC);
      oprot.writeI32(this.updateSequenceNum);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SavedSearch(");
    boolean first = true;

    if (isSetGuid()) {
      sb.append("guid:");
      if (this.guid == null) {
        sb.append("null");
      } else {
        sb.append(this.guid);
      }
      first = false;
    }
    if (isSetName()) {
      if (!first) sb.append(", ");
      sb.append("name:");
      if (this.name == null) {
        sb.append("null");
      } else {
        sb.append(this.name);
      }
      first = false;
    }
    if (isSetQuery()) {
      if (!first) sb.append(", ");
      sb.append("query:");
      if (this.query == null) {
        sb.append("null");
      } else {
        sb.append(this.query);
      }
      first = false;
    }
    if (isSetFormat()) {
      if (!first) sb.append(", ");
      sb.append("format:");
      if (this.format == null) {
        sb.append("null");
      } else {
        sb.append(this.format);
      }
      first = false;
    }
    if (isSetUpdateSequenceNum()) {
      if (!first) sb.append(", ");
      sb.append("updateSequenceNum:");
      sb.append(this.updateSequenceNum);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

