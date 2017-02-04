package ua.gudkov.fp.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.gudkov.fp.dao.api.MessageDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.entity.Message;
import ua.gudkov.fp.entity.SimpleFilter;
import ua.gudkov.fp.service.api.MsgService;

/**
 * Message service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MsgServiceImpl implements MsgService {
	private MessageDAO msgDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(MsgServiceImpl.class);

	public MsgServiceImpl(MessageDAO msgDAO, DBManager dbManager) {
		this.msgDAO = msgDAO;
		this.dbManager = dbManager;
		LOG.debug("Service created");
	}

	@Override
	public boolean addMsg(Message msg) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return msgDAO.insertMsg((msg));
			}
		});
	}

	@Override
	public boolean delMsg(long idMessage) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return msgDAO.delMessage(idMessage);
			}
		});
	}

	@Override
	public List<Message> findAll(SimpleFilter filter) {
		return dbManager.execute(new Operation<List<Message>>() {
			@Override
			public List<Message> execute() {
				return msgDAO.findAll(filter.getStartRow(), filter.getRowNum());
			}
		});
	}

	@Override
	public Message findByIdMessage(long idMessage) {
		return dbManager.execute(new Operation<Message>() {
			@Override
			public Message execute() {
				return msgDAO.findByIdMessage(idMessage);
			}

		});
	}

	@Override
	public long msgNumber() {
		return dbManager.execute(new Operation<Long>() {
			@Override
			public Long execute() {
				return msgDAO.countAllMessages();
			}

		});
	}

}
