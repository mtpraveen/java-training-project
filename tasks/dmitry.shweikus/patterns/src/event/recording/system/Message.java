/**
 * 
 */
package event.recording.system;

import event.recording.enums.MessageKind;

/**
 * @author dima
 *
 */
public class Message
{
	private MessageKind kind;
	private Object extraData;
	/**
	 * @return the kind
	 */
	public MessageKind getKind()
	{
		return kind;
	}
	/**
	 * @return the extraData
	 */
	public Object getExtraData()
	{
		return extraData;
	}
	/**
	 * @param kind
	 * @param extraData
	 */
	public Message(MessageKind kind, Object extraData) {
		super();
		this.kind = kind;
		this.extraData = extraData;
	}
	public Message(MessageKind kind) {
		super();
		this.kind = kind;
		this.extraData = null;
	}
}
